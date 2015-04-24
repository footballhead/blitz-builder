; LoadResources.bb
; Load all resources used
; Michael Hitchens (FootBallHead)
; ----------------------------------------

; TODO
; Add fail to load message

; Define the fonts used
Global arial20b = LoadFont( "Arial" , 40 , True )
Global arial14b = LoadFont( "Arial" , 20 , True )
Global courier10 = LoadFont( "Courier" , 10 )

; Load the sounds
Global snd_jump = LoadSound( "jump.wav" )
Global snd_fall = LoadSound( "fall.wav" )
Global snd_step = LoadSound( "step.wav" )
Global snd_destroy = LoadSound( "destroy.wav" )
Global snd_place = LoadSound( "place.wav" )
;~IDEal Editor Parameters:
;~C#Blitz3D