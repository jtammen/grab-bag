declare
A0 A1 A2  A3 A4 A5  A6 A7 A8
B0 B1 B2  B3 B4 B5  B6 B7 B8
C0 C1 C2  C3 C4 C5  C6 C7 C8
D0 D1 D2  D3 D4 D5  D6 D7 D8
E0 E1 E2  E3 E4 E5  E6 E7 E8
F0 F1 F2  F3 F4 F5  F6 F7 F8
G0 G1 G2  G3 G4 G5  G6 G7 G8
H0 H1 H2  H3 H4 H5  H6 H7 H8
I0 I1 I2  I3 I4 I5  I6 I7 I8
proc {ErstelleFeld Feld}
   % Fuer jede Zelle eine Variable
   Feld = feld(a0:A0 a1:A1 a2:A2  a3:A3 a4:A4 a5:A5  a6:A6 a7:A7 a8:A8
	       b0:B0 b1:B1 b2:B2  b3:B3 b4:B4 b5:B5  b6:B6 b7:B7 b8:B8
	       c0:C0 c1:C1 c2:C2  c3:C3 c4:C4 c5:C5  c6:C6 c7:C7 c8:C8
	       d0:D0 d1:D1 d2:D2  d3:D3 d4:D4 d5:D5  d6:D6 d7:D7 d8:D8
	       e0:E0 e1:E1 e2:E2  e3:E3 e4:E4 e5:E5  e6:E6 e7:E7 e8:E8
	       f0:F0 f1:F1 f2:F2  f3:F3 f4:F4 f5:F5  f6:F6 f7:F7 f8:F8
	       g0:G0 g1:G1 g2:G2  g3:G3 g4:G4 g5:G5  g6:G6 g7:G7 g8:G8
	       h0:H0 h1:H1 h2:H2  h3:H3 h4:H4 h5:H5  h6:H6 h7:H7 h8:H8
	       i0:I0 i1:I1 i2:I2  i3:I3 i4:I4 i5:I5  i6:I6 i7:I7 i8:I8)
   Feld ::: 1#9                    % Basis-Constraint: Werte zwischen 1 und 9
   A1 =: 1 A2 =: 2 A5 =: 9 A6 =: 6 % Vorbelegung Zeile 1
   B2 =: 7 B3 =: 3 B4 =: 5 B7 =: 9 % Vorbelegung Zeile 2
   C0 =: 8 C4 =: 4 C7 =: 3 C8 =: 7 % ...
   D0 =: 4 D2 =: 3 D3 =: 2 D7 =: 1
   E1 =: 8 E3 =: 7 E4 =: 6 E6 =: 9
   F0 =: 9 F5 =: 3 F6 =: 8 F8 =: 4
   G1 =: 6 G5 =: 5 G6 =: 7 G8 =: 1
   H0 =: 7 H2 =: 9 H3 =: 6 H5 =: 1
   I1 =: 5 I4 =: 2 I7 =: 6 I8 =: 9
end

declare Feld Sudoku
proc {Sudoku Feld Loesung}
   Loesung = Feld
   {FD.distinct [A0 A1 A2 A3 A4 A5 A6 A7 A8]} % Constraints Zeile 1
   {FD.distinct [A0 B0 C0 D0 E0 F0 G0 H0 I0]} % Spalte 1
   {FD.distinct [A0 A1 A2 B0 B1 B2 C0 C1 C2]} % Block 1
   {FD.distinct [B0 B1 B2 B3 B4 B5 B6 B7 B8]} % Zeile 2
   {FD.distinct [A1 B1 C1 D1 E1 F1 G1 H1 I1]} % Spalte 2
   {FD.distinct [A3 A4 A5 B3 B4 B5 C3 C4 C5]} % Block 2
   {FD.distinct [C0 C1 C2 C3 C4 C5 C6 C7 C8]} % Zeile 3
   {FD.distinct [A2 B2 C2 D2 E2 F2 G2 H2 I2]} % Spalte 3
   {FD.distinct [A6 A7 A8 B6 B7 B8 C6 C7 C8]} % Block 3
   {FD.distinct [D0 D1 D2 D3 D4 D5 D6 D7 D8]} % Zeile 4
   {FD.distinct [A3 B3 C3 D3 E3 F3 G3 H3 I3]} % Spalte 4
   {FD.distinct [D0 D1 D2 E0 E1 E2 F0 F1 F2]} % Block 4
   {FD.distinct [E0 E1 E2 E3 E4 E5 E6 E7 E8]} % Zeile 5
   {FD.distinct [A4 B4 C4 D4 E4 F4 G4 H4 I4]} % Spalte 5
   {FD.distinct [D3 D4 D5 E3 E4 E5 F3 F4 F5]} % Block 5
   {FD.distinct [F0 F1 F2 F3 F4 F5 F6 F7 F8]} % Zeile 6
   {FD.distinct [A5 B5 C5 D5 E5 F5 G5 H5 I5]} % Spalte 6
   {FD.distinct [D6 D7 D8 E6 E7 E8 F6 F7 F8]} % Block 6
   {FD.distinct [G0 G1 G2 G3 G4 G5 G6 G7 G8]} % Zeile 7
   {FD.distinct [A6 B6 C6 D6 E6 F6 G6 H6 I6]} % Spalte 7
   {FD.distinct [G0 G1 G2 H0 H1 H2 I0 I1 I2]} % Block 7
   {FD.distinct [H0 H1 H2 H3 H4 H5 H6 H7 H8]} % Zeile 8
   {FD.distinct [A7 B7 C7 D7 E7 F7 G7 H7 I7]} % Spalte 8
   {FD.distinct [G3 G4 G5 H3 H4 H5 I3 I4 I5]} % Block 8
   {FD.distinct [I0 I1 I2 I3 I4 I5 I6 I7 I8]} % Zeile 9
   {FD.distinct [A8 B8 C8 D8 E8 F8 G8 H8 I8]} % Spalte 9
   {FD.distinct [G6 G7 G8 H6 H7 H8 I6 I7 I8]} % Block 9
end

local
   N = 3
   Window = {New Tk.toplevel tkInit(title:'Sudoku' width:200 height:200)}
   Mainframe = {New Tk.frame tkInit(parent:Window
				    relief:solid
				    borderwidth:1)}
   Startbutton = {New Tk.button tkInit(parent:Window
				       text:'START'
				       action: proc {$}
						  Feld = {Sudoku Feld}
						     {List.forAllInd {Record.toList Feld}
						      proc {$ Index Wert}
							 Row = (Index-1) div (N*N)
							 Col = (Index-1) mod (N*N)
						      in
							 {Tk.batch [grid(row:Row
									 column:Col
									 {New Tk.label tkInit(parent:Mainframe
											      text:Wert
											      relief:solid
											      borderwidth:1
											      padx:8
											      pady:4
											      font:{New Tk.font tkInit(family:helvetica
														       weight:bold
														       size:12)}
											     )
									 }
									)
								   ]}
						      end}
					       end
				      )}
   Feld = {ErstelleFeld}
in
   {Tk.send pack(Mainframe side:left padx:2 pady:2)}
   {Tk.send pack(Startbutton side:bottom padx:1#m pady:1#m)}
   {List.forAllInd {Record.toList Feld}
    proc {$ Index Wert}
       Row = (Index-1) div (N*N)
       Col = (Index-1) mod (N*N)
       DisplayString
    in
       if ({FD.reflect.size Wert} == 1)
       then DisplayString = {Int.toString Wert}
       else DisplayString = '_' end
       {Tk.batch [grid(row:Row
		       column:Col
		       {New Tk.label tkInit(parent:Mainframe
					    text:DisplayString
					    relief:solid
					    borderwidth:1
					    padx:8
					    pady:4
					    font:{New Tk.font tkInit(family:helvetica
								     weight:bold
								     size:12)}
					   )
		       }
		      )
		 ]}
    end}
end