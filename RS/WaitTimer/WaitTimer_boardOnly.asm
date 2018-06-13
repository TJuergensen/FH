/*
 * WaitTimer.asm
 *
 *  Created: 14.12.2012 21:33:59
 *   Author: Ole
 */ 
.include "m644PAdef.inc"	; device: ATmega 644PA
	.cseg			; dies ist Code
; ----- Am Beginn des Codespeichers liegt die Vektortabelle -----
;		Hier nur Sprungtabelle anlegen. Mindestens f�r den 
;		Reset-Interrupt (An Adresse 0):
	.org	0		; Am Anfang des Code-Speicher 
	jmp start		; Sprung bei reset
	.org OC1Aaddr	; Timer1 Overflow Interrupt
	jmp OCR1A_isr	; Timer1 Overflow Interrupt
; hier folgen weitere Interrupts, wenn ben�tigt
;	jmp TIMER-ISR	; Sprungvektor 
; ***** Hier werden die "Variablen" definiert
	.dseg			; dies sind Daten
T1count:	.byte 1	; Z�hler f�r Wartefunktion mit Timer1
	.cseg			; dies ist Code
; ----- Platz um .equ definitionen vorzunehmen
.equ	T1Counter = 98			; Timer-Wert f�r 0,1s
;.equ	T1Counter = 20			; Debug-Timer-Wert f�r xxs
; ----- Programm beginnt hinter der Vektortabelle: -----
	.org 2*INT_VECTORS_SIZE
; hier kommen die Interrupt Service Routinen hin, wenn benutzt
/********************************************************/
;	ISR Timer1 Compare Interrupt A
OCR1A_isr:	
	push r16
	lds r16, T1count		; Z�hlwert lesen
	tst r16					; schon Null?
	breq noDec				; ja: nichts weiter Abziehen
	dec r16					; T1count--
	sts T1count, r16		; und abspeichern
noDec:
	pop r16					; Register restaurierten
	reti					; Return from Interrupt;
;


; ----- Initialisierung von Timer1 auf 0,1s Periode -----
InitT1:
	; beim 644PA sind Timer1-Register Memory.mapped
	push r17
	ldi r17, HIGH(T1Counter)	; Timer-Schwelle 
	sts OCR1AH, r17				; byte-weise 
	ldi r17, LOW(T1Counter)		; nach OCR1A
	sts OCR1AL, r17
	clr r17					; r17=0 
	sts TCNT1H, r17			; Counter=0
	sts TCNT1L, r17
	sts TCCR1A, r17			; configure to CTC-Mode	
	ldi r17, 0b0001101		; CTC-mode, prescaler 1024
	sts TCCR1B, r17				
	; und den OC1A intrerrupt einschalten:
	lds r17, TIMSK1			; Interruptmaske Timer1 
	ori r17, 0b00000010		; OCIE1A setzen
	sts TIMSK1, r17			; und abspeichern
	pop r17
	ret

; ----- Warte-Z�hler Setzen und Starten (nonblocking)
startWait:
	sts T1count, r16		; Nur Z�hler setzen
	ret
; ----- Wait-funktion komplett
; Parameter: R16 enth�lt die Wartedauer in Zehntelsekunden
wait:
; Timer resetten, Interrupt aktivieren
	sts T1count, r16		
; ----- Rest der Komplett-Funktion als Abwarte-Funktion -----
waitNow:
; polling auf Counter=0
	lds r16, T1count
	tst r16
	brne waitNow
	ret

; ***** Einsprungpunkt in das Hauptprogramm *****
start:
; ----- Initialisierung der grundlegenden Funktionen -----
	; Initialisieren des Stacks am oberen Ende des RAM
    ; 16 bit SP wird als SPH:SPL im IO-Space angesprochen 
    ldi r16, LOW(RAMEND)	; low-Byte von RAMEND nach r16
    out SPL, r16	; in low-byte des SP ausgeben
					; der SP liegt im IO-Space 
    ldi r16, HIGH(RAMEND)	; high-Byte von RAMEND nach r16
    out SPH, r16		; in high-byte des SP ausgeben
    ; ab hier kann der Stack verwendet werden 

	call InitT1			; Timer 1 initialisieren
	sei					; global Interrupt enable
	; testroutine: Lauflicht auf PortD
	ldi r17, 0xFF		; PortD als Ausgabe
	out DDRD, r17		; PortD als Ausgabe
	ldi r17, 0x01		; Initialisieren des Lauflichtes
loop:	
	out PORTD, r17		; Lauflicht von r17 nach PortD
	rol r17				; n�chstes Lichtmuster
	ldi r16, 5			; 0,5s Wartezeit
	call wait			; warten
	out PORTD, r17		; Lauflicht ausgeben
	sbis PINA,5	; Verlasse Schleife, wenn [CANCEL] gedr�ckt
	jmp loop			; Sonst springe zum Schleifenanfang 
	ldi R17,0			; Ende: Licht aus
	out PORTD, r17		; und ausgeben
stay:
	jmp stay			; Endlosschleife
