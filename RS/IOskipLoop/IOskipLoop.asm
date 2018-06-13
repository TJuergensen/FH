/*
 * IOskipLoop.asm
 *
 *  Created: 14.12.2012 21:11:51
 *   Author: Ole
 */ 
.include "m644PAdef.inc"	; device: ATmega 644PA

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


	push r20		; r20 auf den Stack retten
	ser r20			; r20=0xFF
    out DDRD, r20	; PortD als Ausgang verwenden
	ldi r20, 0x55	; Warte-Anzeige definieren
	out PORTD, r20	; und ausgeben
waitkey:
	nop
	sbis PINA,4	; Verlasse Schleife, wenn [ENTER] gedrückt
	jmp waitkey		; Sonst springe zum Schleifenanfang 
	ser r20			; flag Ende 
	out PORTD, r20	; und ausgeben
	pop r20			; Restaurieren des Zählregisters
stay:
	jmp stay		; Endlosschleife
	

