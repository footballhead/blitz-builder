;METHOD 1
;Const GameUPS=60 ; Updates per second
;Local Period=1000/GameUPS 
;Local FrameTime=MilliSecs()-Period
;
;Local Tween#, Ticks,i,Remaining,StartTime,Elapsed
;
;While Not KeyHit(1)
;	
;	StartTime = MilliSecs()
;	
;	Repeat
;		Elapsed=MilliSecs()-FrameTime
;	Until Elapsed
;	
;	Ticks=Elapsed / Period
;	Tween=Float(Elapsed Mod Period)/Float(Period)
;	
;	For i=1 To Ticks
;		FrameTime=FrameTime+Period
;		If i=Ticks Then
;			CaptureWorld
;		End If
;		UpdateGame()
;		UpdateWorld
;	Next
;	RenderWorld Tween
;	
;	Remaining = Period - (MilliSecs() - StartTime)
;	If Remaining > 1 Then 
;		Delay (Remaining-1) ; Free some CPU time
;	End If
;	
;	DrawImage img_cro,320,240,0
;	Flip
;	
;Wend
;
;End

;METHOD 2
;-- Timing.
Global milli_secs ; Holds the value of the Millisecs() timer. Set at the start of the main loop.
Global old_time ; This must be set to the current 'MilliSecs()' time at the start of a new game and when returning from a pause.
Global game_time ; This holds a relative game time value which is used with timeouts so that they can be paused.
Global Delta_Time# ; Use this as a multiplier for all continuous game world events, to regulate game speed.
;^^^^^^

Function CalculateDeltatimeAndGametime()
; NOTES:
; The variable 'old_time' must be set to the current millisecs time at the start of a new game and when returning from a pause.
	
	milli_secs = MilliSecs () ; Store the 'MilliSecs ()' time in the 'milli_secs' variable so that you can use the stored time value without calling 'MilliSecs ()' again in this loop.
	
	the_time_taken = milli_secs - old_time ; Calculate the time the last loop took to execute.
	If the_time_taken > 100 Then the_time_taken = 100 ; This stops disk accesses and the like from causing jumps in position.
	game_time = game_time + the_time_taken ; Calculate the value for the 'game_time' variable used with timeouts, etc.
	Delta_Time# = the_time_taken / 1000.0 ; Calculate the value for the 'Delta_Time#' variable used to regulate game speed.
	old_time = milli_secs ; Update the 'old_time' variable with the current time.
	
End Function
;~IDEal Editor Parameters:
;~C#Blitz3D