; Minecraft Rip-Off
; Because I had nothing better to do with my Saturday
; Michael Hitchens (FootBallHead)
; ---------------------------------------

; =>=>=>=>=>=>=>=>=>=>=>=>=>=>=>=>=>=>=>=>=>=>=>=>=>=>=>=>
; TODO LIST
;  + Gravity
;  + Collisions
;  + Cube creation check
;  + Cube array??
;  + Fog
;  + Particles
;  + Console?
;  + Maybe ambient occlusion?????? Maybe??
;  + Menu
; <=<=<=<=<=<=<=<=<=<=<=<=<=<=<=<=<=<=<=<=<=<=<=<=<=<=<=<=
Include "RGBString.bb"
Include "InGameMenu.bb"

; Collisions types
Const TYPE_NONE = 0 , TYPE_PLAYER = 1 , TYPE_BOX = 2

; Self-explanatory functions
Graphics3D 640 , 480 , 16 , 2
SetBuffer BackBuffer( )
AmbientLight 255 , 255 , 255
HidePointer

Global tex_blank2 = CreateTexture( 16  , 16  )
DrawBlankTexture( tex_blank2 , 2 ) ; only make a power of 2!

Global arial20b = LoadFont( "Arial" , 40 , True )
Global arial14b = LoadFont( "Arial" , 20 , True )
Global courier10 = LoadFont( "Courier" , 10 )

; Related to movement
Global noclip = True

Global menu = False

; FPS camera + variables
Global camera = CreateCamera( )
EntityType camera , TYPE_PLAYER
PositionEntity camera , 0 , 0 , 0
CameraRange camera,0.25,200

Global pitch# = 0.0 , yaw# = 0 , roll# = 0
Global lPitch# = 0.0 , lYaw# = 0.0 , lRoll# = 0.0

; Setup visible world
Global skybox = CreateSkybox( )
GenerateWorld( )

While Not KeyDown( 1 )
	
	If KeyHit( 57 ) Then
		menu = Not menu
		If menu
			ShowPointer
		Else
			HidePointer
			MoveMouse 320 , 240
		EndIf
	EndIf
	
	If menu = False
		MoveCamera( )
		UpdateCameraLook( )
		If MouseHit( 1 ) Then Pick( )
		If MouseHit( 2 ) Then CubeRemove( )
	EndIf
	
	;GOOD ALGOR
	;PositionEntity miscCube , EntityX#( camera ) + Cos#( yaw# + 90 ) , EntityY#( camera ) , EntityZ#( camera ) + Sin#( yaw# + 90)
	;PositionEntity miscCube , EntityX#( camera ) , EntityY#( camera ) - Sin#( pitch# ) , EntityZ#( camera ) + Cos#( pitch# )
	;PositionEntity miscCube , EntityX#( camera ) + Cos#( pitch# ) , EntityY#( camera ) - Sin#( pitch# ) , EntityZ#( camera )
	
	RenderWorld
		
	If menu = False
		DrawHUD( )
	Else
		DrawMenu( )
	EndIf
	VWait 10 ; Make sure there is no tearing (god I hate tearing)
	Flip False
Wend

End

Function DrawBlankTexture( tex , div )
	Local w = TextureWidth( tex )
	Local h = TextureHeight( tex )
	Local check = True
	SetBuffer TextureBuffer( tex )
	
	ClsColor 255 , 255 , 255 : Cls
	Color 192 , 192 , 192
	For y = 0 To div - 1
		For x = 0 To div - 1
			If check Then Rect w/div*(x) , h/div*y , w/div , h/div , True
			check = Not check
		Next		check = Not check
	Next
	
	SetBuffer BackBuffer( )
End Function	
	
Function CreateBrushAdv( tex=0 , red=255 , gre=255 , blu=255 )
	Local brush = CreateBrush( red , gre , blu )
	If (tex = 0) Then tex = tex_blank2
	BrushTexture brush , tex
	Return brush
End Function

Function CreateBrushBlankRGB( RGBString$ )
	Local brush = CreateBrush( DisectRGBString( RGBString$ , RGB_RED ) , DisectRGBString( RGBString$ , RGB_GREEN ) , DisectRGBString( RGBString$ , RGB_BLUE ) )
	BrushTexture brush , tex_blank2
	Return brush
End Function

; Rotate camera based on mouse movements (all FPS-like)
Function UpdateCameraLook( )
	pitch# = pitch# + MouseYSpeed( )
	yaw# = yaw# - MouseXSpeed( )
	RotateEntity camera , pitch# , yaw# , roll#
	lPitch# = pitch#
	lYaw# = yaw#
	lRoll# = roll#
	MoveMouse 320 , 240
End Function

; This doozy simply creates a cube with customizable faces
Function CreateSixCube( xx# , yy# , zz# , up=0 , down=0 , side=0 )
	Local mesh , surf
	Local v0 , v1 , v2 , tri
	
	mesh = CreateMesh( )
	surf = CreateSurface( mesh , side ) ;front
	v0 = AddVertex( surf , -1 ,  1 , 0 , 0 , 0 )
	v1 = AddVertex( surf , -1 , -1 , 0 , 0 , 1 )
	v2 = AddVertex( surf ,  1 , -1 , 0 , 1 , 1 )
	tri = AddTriangle( surf , v0 , v2 , v1 )
	v0 = AddVertex( surf , -1 ,  1 , 0 , 0 , 0 )
	v1 = AddVertex( surf ,  1 ,  1 , 0 , 1 , 0 )
	v2 = AddVertex( surf ,  1 , -1 , 0 , 1 , 1 )
	tri = AddTriangle( surf , v0 , v1 , v2 )
	
	surf = CreateSurface( mesh , side ) ;right
	v0 = AddVertex( surf , 1 ,  1 ,  0 , 0 , 0 )
	v1 = AddVertex( surf , 1 , -1 ,  0 , 0 , 1 )
	v2 = AddVertex( surf , 1 , -1 ,  2 , 1 , 1 )
	tri = AddTriangle( surf , v0 , v2 , v1 )
	v0 = AddVertex( surf , 1 ,  1 ,  0 , 0 , 0 )
	v1 = AddVertex( surf , 1 ,  1 ,  2 , 1 , 0 )
	v2 = AddVertex( surf , 1 , -1 ,  2 , 1 , 1 )
	tri = AddTriangle( surf , v0 , v1 , v2 )
	
	surf = CreateSurface( mesh , side ) ;back
	v0 = AddVertex( surf ,  1 , -1 , 2 , 0 , 1 )
	v1 = AddVertex( surf ,  1 ,  1 , 2 , 0 , 0 )
	v2 = AddVertex( surf , -1 ,  1 , 2 , 1 , 0 )
	tri = AddTriangle( surf , v0 , v1 , v2 )
	v0 = AddVertex( surf ,  1 , -1 , 2 , 0 , 1 )
	v1 = AddVertex( surf , -1 , -1 , 2 , 1 , 1 )
	v2 = AddVertex( surf , -1 ,  1 , 2 , 1 , 0 )
	tri = AddTriangle( surf , v0 , v2 , v1 )
	
	surf = CreateSurface( mesh , side ) ;left
	v0 = AddVertex( surf , -1 , -1 ,  2 , 0 , 1 )
	v1 = AddVertex( surf , -1 ,  1 ,  2 , 0 , 0 )
	v2 = AddVertex( surf , -1 ,  1 ,  0 , 1 , 0 )
	tri = AddTriangle( surf , v0 , v1 , v2 )
	v0 = AddVertex( surf , -1 , -1 ,  2 , 0 , 1 )
	v1 = AddVertex( surf , -1 , -1 ,  0 , 1 , 1 )
	v2 = AddVertex( surf , -1 ,  1 ,  0 , 1 , 0 )
	tri = AddTriangle( surf , v0 , v2 , v1 )
	
	surf = CreateSurface( mesh , up ) ;up
	v0 = AddVertex( surf , -1 ,  1 , 2 , 0 , 0 )
	v1 = AddVertex( surf , -1 ,  1 , 0 , 0 , 1 )
	v2 = AddVertex( surf ,  1 ,  1 , 0 , 1 , 1 )
	tri = AddTriangle( surf , v0 , v2 , v1 )
	v0 = AddVertex( surf , -1 ,  1 ,  2 , 0 , 0 )
	v1 = AddVertex( surf ,  1 ,  1 ,  2 , 1 , 0 )
	v2 = AddVertex( surf ,  1 ,  1 ,  0 , 1 , 1 )
	tri = AddTriangle( surf , v0 , v1 , v2 )
	
	surf = CreateSurface( mesh , down ) ;down
	v0 = AddVertex( surf ,  1 ,  -1 , 0 , 0 , 0 )
	v1 = AddVertex( surf ,  1 ,  -1 , 2 , 0 , 1 )
	v2 = AddVertex( surf , -1 ,  -1 , 2 , 1 , 1 )
	tri = AddTriangle( surf , v0 , v1 , v2 )
	v0 = AddVertex( surf ,  1 ,  -1 ,  0 , 0 , 0 )
	v1 = AddVertex( surf , -1 ,  -1 ,  0 , 1 , 0 )
	v2 = AddVertex( surf , -1 ,  -1 ,  2 , 1 , 1 )
	tri = AddTriangle( surf , v0 , v2 , v1 )
	
	UpdateNormals mesh
	EntityPickMode mesh , 2
	PositionEntity mesh , xx# , yy# , zz#
	EntityType mesh , TYPE_BOX
	Return mesh
End Function

; Calls CreateSixCube with brushes of colour
Function CreateSixCubeColor( xx# , yy# , zz# , up$=WHITE_RGB$ , down$=WHITE_RGB$ , side$=WHITE_RGB$ )
	Return CreateSixCube( xx# , yy# , zz# , CreateBrushBlankRGB( up$ ) , CreateBrushBlankRGB( down$ ) , CreateBrushBlankRGB( side$ ) )
End Function

; Create and set properties of skybox
Function CreateSkybox( )
	Local tex_side = LoadTexture( "sky_l.bmp" )
	ScaleTexture tex_side , 1 , 1.1
	PositionTexture tex_side , -5 , -5
	
	Local bru_up = CreateBrushAdv( LoadTexture( "sky_u.bmp" ) )
	Local bru_down = CreateBrushAdv( LoadTexture( "sky_d.bmp" ) )
	Local bru_side = CreateBrushAdv( tex_side )
	
	Local box = CreateSixCube( 0 , 0 , 0 , bru_up , bru_down , bru_side )
	ScaleEntity box , 100 , 100 , 100
	PositionEntity box, 0 , 0 , -100
	FlipMesh box
	EntityPickMode box , 0 
	Return box
	PositionTexture tex_side , -5 , -5
End Function

; Keyboard checks related to the camera
Function MoveCamera()
	If (Not noclip) RotateEntity camera,0,yaw#,0
	If KeyDown(17) Then MoveEntity camera,0,0,0.1
	If KeyDown(30) Then MoveEntity camera,-0.1,0,0
	If KeyDown(31) Then MoveEntity camera,0,0,-0.1
	If KeyDown(32) Then MoveEntity camera,0.1,0,0
End Function

; Create new cube around existing cube at crosshairs 
Function Pick( )
	Local picked = LinePick( EntityX#( camera ) , EntityY#( camera ) , EntityZ#( camera ) , Cos#( yaw# + 90 ) * Cos#( pitch# ) * 8 , - Sin#( pitch# ) * 8 , Sin#( yaw# + 90) * Cos#( pitch# ) * 8 )
	If (Not picked = 0)
		CreateSixCubeColor( EntityX#( picked ) + PickedNX#( ) * 2 , EntityY#( picked ) + PickedNY#( ) * 2 , EntityZ#( picked ) + PickedNZ#( ) * 2 , BLUE_RGB$ , PURPLE_RGB$ , PURPLE_RGB$ )		
	EndIf
End Function

; Check and remove selected cube within reach
Function CubeRemove( )
	Local picked = LinePick( EntityX#( camera ) , EntityY#( camera ) , EntityZ#( camera ) , Cos#( yaw# + 90 ) * Cos#( pitch# ) * 4 , - Sin#( pitch# ) * 4 , Sin#( yaw# + 90) * Cos#( pitch# ) * 4 )
	If (Not picked = 0)
		FreeEntity picked
	EndIf
End Function

; Create the default terrain
Function GenerateWorld( )
	For z = 0 To 10
		For y = 0 To 3
			For x = 0 To 10
				CreateSixCubeColor( x * 2 , ( y + 2 ) * -2 , z * 2 , GREEN_RGB$ , BROWN_RGB$ , BROWN_RGB$ )
			Next
		Next
	Next
End Function

; Draw the 2D elements of the HUD
Function DrawHUD( )
	SetFont courier10
	Color 255 , 255 , 255
	;debug messages go here
	Color 255 , 0 , 0
	Text 320 , 240 , "( + )" , True , True
End Function

; Well... um... draw the menu... duh
Function DrawMenu( )
	SetFont arial20b
	Color 255 , 255 , 255
	Text 320 , 40 , "Blitz3D Builder" , True , True
	SetFont arial14b
	Text 320 , 80 , optionName$( 0 )+"< " , True , True
End Function
;~IDEal Editor Parameters:
;~C#Blitz3D