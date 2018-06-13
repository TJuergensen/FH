/*
 * Switch-case.asm
 *
 *  Created: 14.12.2012 18:09:17
 *   Author: Ole
 */ 
.include "m644PAdef.inc"	; device: ATmega 644PA

	.cseg			; dies ist Code
; ----- Am Beginn des Codespeichers liegt die Vektortabelle -----
;		Hier nur Sprungtabelle anlegen. Mindestens für den 
;		Reset-Interrupt (An Adresse 0):
	.org	0		; Am Anfang des Code-Speicher 
	jmp start		; Sprung bei reset
	.org OC1Aaddr	; Timer1 Overflow Interrupt
	reti
	; jmp OCR1A_isr	; Timer1 Overflow Interrupt
; hier folgen weitere Interrupts, wenn benötigt
;	jmp TIMER-ISR	; Sprungvektor 
; ***** Hier werden die "Variablen" definiert
; Datensegment (Variablen im SRAM)
	.dseg
sel:	.byte 1			; reserviert 1 Byte

	.cseg			; dies ist Code
; ----- Platz um .equ definitionen vorzunehmen
.equ	Fall1 = 1		; Namen für cases
.equ	Fall2 = 8
; ----- Programm beginnt hinter der Vektortabelle: -----
	.org 2*INT_VECTORS_SIZE
; hier kommen die Interrupt Service Routinen hin, wenn benutzt
/********************************************************/


;
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
init:
	ser r16
	out DDRA, r16	; PortA als Augabe
	out DDRB, r16	; PortB als Augabe
	out DDRD, r16	; PortD als Augabe
	ldi r16, 8		; Debug-Wert bestimmen    
	sts sel, r16	; Debug-Wert in den Speicher    

; switch-case in ASM zur Variable sel
	lds r0, sel		; sel wird geprüft
c1:					; case 1:
	ldi r16, Fall1	; vergleich mit Konstanten in r16
	cpse r0, r16	; sel=Fall1 ? 
	jmp c2			; case 1 ist es nicht
	neg r0			; case 1 code
	out PORTA, r0	; ausgeben
	jmp endcase		; = break;
c2:					; case 2 
	ldi r16, Fall2	; vergleich mit Konstanten in r16
	cpse r0, r16	; sel=Fall2 ? 
	jmp default		; case 2 ist es nicht
	neg r0			; case 2 code
	out PORTB, r0	; ausgeben
	jmp endcase		; = break;
default:			; auch hier gibt es einen default
	out PORTD, r0
endcase:
	nop				; nach der switch-case 
stay:
	jmp stay		; Endlosschleife
