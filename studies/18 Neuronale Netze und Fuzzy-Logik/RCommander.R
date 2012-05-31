Thyroid <- read.table("D:/jan/Studium/8. Semester/Neuronale Netze und Fuzzy-Logik/Gruppenarbeit/Ausarbeitung/t.txt", header=FALSE, sep="", na.strings="NA", dec=".", strip.white=TRUE)
showData(Thyroid, placement='-20+200', font=getRcmdr('logFont'), maxwidth=80, maxheight=30)
showData(Thyroid, placement='-20+200', font=getRcmdr('logFont'), maxwidth=80, maxheight=30)
names(Thyroid)
data()
save("Thyroid", file="D:/jan/Studium/8. Semester/Neuronale Netze und Fuzzy-Logik/Gruppenarbeit/Ausarbeitung/Thyroid.rda")
help("Thyroid")
names(Thyroid)[c(22)] <- c("Klasse")
showData(Thyroid, placement='-20+200', font=getRcmdr('logFont'), maxwidth=80, maxheight=30)
save("Thyroid", file="D:/jan/Studium/8. Semester/Neuronale Netze und Fuzzy-Logik/Gruppenarbeit/Ausarbeitung/Thyroid.rda")
summary(Thyroid)
sapply(Thyroid, function(x)(sum(is.na(x)))) # NA counts
cor(Thyroid[,c("Klasse","V1","V10","V11","V12","V13","V14","V15","V16","V17","V18","V19","V2","V20","V21","V3","V4","V5","V6","V7","V8","V9")], use="complete.obs")
cor.test(Thyroid$Klasse, Thyroid$V1, alternative="two.sided", method="pearson")
shapiro.test(Thyroid$Klasse)
.cluster <- KMeans(model.matrix(~-1 + V1 + V10 + V11 + V12 + V13 + V14 + V15 + V16 + V17 + V18 + V19 + V2 + V20 + V21 + V3 + V4 + V5 + V6 + V7 + V8 + V9, Thyroid), centers = 3, iter.max = 10, num.seeds = 10)
.cluster$size # Cluster Sizes
.cluster$centers # Cluster Centroids
.cluster$withinss # Within Cluster Sum of Squares
.cluster$tot.withinss # Total Within Sum of Squares
.cluster$betweenss # Between Cluster Sum of Squares
biplot(princomp(model.matrix(~-1 + V1 + V10 + V11 + V12 + V13 + V14 + V15 + V16 + V17 + V18 + V19 + V2 + V20 + V21 + V3 + V4 + V5 + V6 + V7 + V8 + V9, Thyroid)), xlabs = as.character(.cluster$cluster))
remove(.cluster)
plot(Thyroid$Klasse, type="h")
.cluster <- KMeans(model.matrix(~-1 + V1 + V10 + V11 + V12 + V13 + V14 + V15 + V16 + V17 + V18 + V19 + V2 + V20 + V21 + V3 + V4 + V5 + V6 + V7 + V8 + V9, Thyroid), centers = 3, iter.max = 10, num.seeds = 10)
.cluster$size # Cluster Sizes
.cluster$centers # Cluster Centroids
.cluster$withinss # Within Cluster Sum of Squares
.cluster$tot.withinss # Total Within Sum of Squares
.cluster$betweenss # Between Cluster Sum of Squares
biplot(princomp(model.matrix(~-1 + V1 + V10 + V11 + V12 + V13 + V14 + V15 + V16 + V17 + V18 + V19 + V2 + V20 + V21 + V3 + V4 + V5 + V6 + V7 + V8 + V9, Thyroid)), xlabs = as.character(.cluster$cluster))
remove(.cluster)
.PC <- princomp(~V1+V10+V11+V12+V13+V14+V15+V16+V17+V18+V19+V2+V20+V21+V3+V4+V5+V6+V7+V8+V9, cor=TRUE, data=Thyroid)
unclass(loadings(.PC))  # component loadings
.PC$sd^2  # component variances
remove(.PC)
.FA <- factanal(~V1+V10+V11+V12+V13+V14+V15+V16+V17+V18+V19+V2+V20+V21+V3+V4+V5+V6+V7+V8+V9, factors=3, rotation="varimax", scores="none", data=Thyroid)
.FA
remove(.FA)
Hist(Thyroid$Klasse, scale="frequency", breaks="Sturges", col="darkgray")
scatterplot(V1~Klasse, reg.line=lm, smooth=TRUE, labels=FALSE, boxplots='xy', span=0.5, xlab="<automatisch>", ylab="<automatisch>", pch=c(<automatisch>), data=Thyroid)
scatter3d(Thyroid$V1, Thyroid$Klasse, Thyroid$V10, fit="linear", residuals=TRUE, bg="white", axis.scales=TRUE, grid=TRUE, ellipsoid=FALSE, xlab="V1", ylab="Klasse", zlab="V10")
plot(Thyroid$Klasse, type="h")
stem.leaf(Thyroid$Klasse)
boxplot(Thyroid$Klasse, ylab="Klasse")
matplot(Thyroid$Klasse, Thyroid[, c("V1")], type="b", lty=1, ylab="V1", pch=1)
matplot(Thyroid$Klasse, Thyroid[, c("V1","V10")], type="b", lty=1, ylab="(1) V1, (2) V10")
Hist(Thyroid$Klasse, scale="percent", breaks="Sturges", col="darkgray")
summary(Thyroid)
names(Thyroid)
reliability(cov(Thyroid[,c("Klasse","V1","V10","V11","V12","V13","V14","V15","V16","V17","V18","V19","V2","V20","V21","V3","V4","V5","V6","V7","V8","V9")], use="complete.obs"))
scatterplot.matrix(~Klasse+V1+V10, reg.line=lm, smooth=TRUE, span=0.5, diagonal = 'density', data=Thyroid)
fix(Thyroid)
showData(Thyroid, placement='-20+200', font=getRcmdr('logFont'), maxwidth=80, maxheight=30)
showData(Thyroid, placement='-20+200', font=getRcmdr('logFont'), maxwidth=80, maxheight=30)
sapply(Thyroid, function(x)(sum(is.na(x)))) # NA counts
fix(Thyroid)
summary(Thyroid)
s.distri
table(Thyroid$Klasse)/length(Thyroid$Klasse)Hist(Thyroid$Klasse, scale="frequency", breaks="Sturges", col="darkgray")
Hist(Thyroid$Klasse, scale="percent", col="darkgray")
Hist(Thyroid$Klasse, xlab="Klasse", ylab="Prozent", scale="percent", col="darkgray", prob=T)
showData(Thyroid, placement='-20+200', font=getRcmdr('logFont'), maxwidth=80, maxheight=30)
hist(Thyroid$Klasse, axes=FALSE, xlab="Klasse", ylab="Prozent", main="", probability=TRUE, col="darkgray")
Hist(Thyroid$Klasse, scale="percent", breaks="Sturges", col="darkgray")

