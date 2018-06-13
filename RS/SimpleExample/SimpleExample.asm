/*
 * SimpleExample.asm
 * Ein erstes Assemblerprogramm für den AVR
 *
 *  Created: 10.12.2012 09:30:40
 *  Author: Ole
 */ 


; Kommentare starten mit ';'
/* oder wie in C */
.include "m644PAdef.inc"	; device: ATmega 644PA

; Bis hier soll gezählt werden: 
.equ maxCount = 10	; entspricht dem #define in C
.equ B = 0			; kann per Namen zugegriffen werden

; Speicherzellen für die Variable A: 
; Datensegment (Variablen im SRAM)
	.dseg
A:	.byte 1			; reserviert 1 Byte

	.cseg			; Hier beginnt der Programmcode
start:
	ser R16			; R16=0xFF
	out DDRA, R16	; PORTA als Ausgabe konfigurieren
	ldi R16, B		; initialisieren: B=0 in R16
	; B in R16, damit cpi funktioniert --> ? 
	clr R1			; oder so: R1=0 (für A)
	sts A, R1		; im SRAM ablegen (A=0)
	out PORTA, R1	; Ausgabe löschen
loop:				; Sprunglabel für die Schleife
	inc R16			; B=B+1
	add R1, R16		; A=A+B
	cpi R16, maxCount; B=maxCount?
	breq output
	jmp loop
output:
	sts A, R1		; Ergebnis im SRAM ablegen
	out PORTA, R1	; Ergebnis ausgeben
stay:
	jmp stay		; loop forever
