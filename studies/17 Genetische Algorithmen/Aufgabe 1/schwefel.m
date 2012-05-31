[X,Y] = meshgrid(-500:5:500);
Z = -X.*sin(sqrt(abs(X))) - Y.*sin(sqrt(abs(Y)));
mesh(X,Y,Z,'EdgeColor','black');

min = (-420.9687)*sin(sqrt(420.9687)) - (420.9687)*sin(sqrt(420.9687));
min