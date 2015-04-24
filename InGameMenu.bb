; InGameMenu.bb
; For all your ingame menu needs
; Michael Hitchens ( FootBallHead )
; -----------------------------------

; TODO
; Make this actually work

Type MenuItem
	Field title$
	Field x
	Field y
	Field font
	Field selected
	Field valueFloat0# , valueFloat1# , valueFloat2# , valueFloat3#
	Field valueMask0$ , valueMask1$ , valueMask2$ , valueMask3$
End Type

Dim items.MenuItem( 2 )
items( 0 ) = CreateMenuItem( 0 , 0 , "Texture size" , 0 , 0 )
;SetMenuItemValues items( 0 ) , 16.0 , 64.0 , 256.0 , 512.0
SetMenuItemMasks items( 0 ) , "16x16" , "64x64" , "256x256" , "512x512"
items( 1 ) = CreateMenuItem( 0 , 0 , "Resolution" , 0 , 0 )
SetMenuItemMasks items( 1 ) , "640x480" , "800x600" , "640x360" , "1280x720"
items( 2 ) = Null

Function CreateMenuItem.MenuItem( x , y , title$ , font , selected = 0 )
	Local m.MenuItem = New MenuItem
	m\x = x
	m\y = y
	m\title$ = title$
	m\font = font
	m\selected = selected
	Return m
End Function

Function SetMenuItemValues( m.MenuItem , one# , two# , three# , four# )
	m\valueFloat0# = one#
	m\valueFloat1# = two#
	m\valueFloat2# = three#
	m\valueFloat3# = four#
End Function

Function SetMenuItemMasks( m.MenuItem , one$ , two$ , three$ , four$ )
	m\valueMask0$ = one$
	m\valueMask1$ = two$
	m\valueMask2$ = three$
	m\valueMask3$ = four$
End Function

Function MenuItemValue#( m.MenuItem , index )
	Select index
		Case 0
			Return m\valueFloat0#
		Case 1
			Return m\valueFloat1#
		Case 2
			Return m\valueFloat2#
		Case 3
			Return m\valueFloat3#
	End Select
End Function

Function MenuItemMask$( m.MenuItem , index )
	Select index
		Case 0
			Return m\valueMask0$
		Case 1
			Return m\valueMask1$
		Case 2
			Return m\valueMask2$
		Case 3
			Return m\valueMask3$
	End Select
End Function

Function MenuItemFont( m.MenuItem )
	Return m\font
End Function

Function MenuItemX( m.MenuItem )
	Return m\x
End Function

Function MenuItemY( m.MenuItem )
	Return m\y
End Function

Function MenuItemTitle$( m.MenuItem )
	Return m\title$
End Function

Function MenuItemSelected( m.MenuItem )
	Return m\selected
End Function
;~IDEal Editor Parameters:
;~C#Blitz3D