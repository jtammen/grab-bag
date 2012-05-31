#!/bin/bash

# 'Debugging'
#set -xv 

#************************************************#
# adressliste.sh                                 #
# Autor: Jan Tammen                              #
# E-Mail: jan.tammen at fh-konstanz dot de       #
# Matrikelnummer: 277143                         #
# Datum: 21.11.2004                              #
#                                                #
# Programmzweck: Verwaltung einer textbasierten  #
#                Adressliste                     #
#************************************************#

# Datei, in welcher Daten gespeichert werden 
DATA_FILE=adressliste.dat

# Feld-Seperator Semikolon 
IFS=";"

# --------------------------------------------------------- #
# setup()                                                   #
# Prueft, ob Datendatei vorhanden. Wenn nicht, anlegen      #
# --------------------------------------------------------- #
setup()
{
    if [ ! -w "$DATA_FILE" ]
    then
        touch "$DATA_FILE"
    fi

    return 0
}

# --------------------------------------------------------- #
# main()                                                    #
# Gibt das Auswahlmenue aus und verzweigt zw. verschiedenen #
# Optionen                                                  #
# --------------------------------------------------------- #
main()
{
    # Prompt fuer select setzen
    PS3="Option waehlen (1-7), ENTER fuer Liste: "

    # Kommando-Eintraege
    KOMMANDO1="Neuen Eintrag erstellen"
    KOMMANDO2="Alle Eintraege anzeigen (unsortiert)"
    KOMMANDO3="Alle Eintraege anzeigen (sortiert nach Name)"
    KOMMANDO4="Suche nach Telefonnummer"
    KOMMANDO5="Suche nach Name"
    KOMMANDO6="Eintrag loeschen"
    KOMMANDO7="Beenden"
   
    # Auswahl-Menue anzeigen 
    select kommando in "$KOMMANDO1" \
                       "$KOMMANDO2" \
                       "$KOMMANDO3" \
                       "$KOMMANDO4" \
                       "$KOMMANDO5" \
                       "$KOMMANDO6" \
                       "$KOMMANDO7" ;
    do
        case "$kommando" in 
            # Neuen Eintrag erstellen
            "$KOMMANDO1" ) daten_einlesen 
                           ;;
                           
            # Alle Eintraege anzeigen, unsortiert
            "$KOMMANDO2" ) ausgabe_liste
                           ;;
            # Alle Eintraege anzeigen, nach Name (1. Feld) sortiert
            "$KOMMANDO3" ) ausgabe_liste 1
                           ;;
                           
            # Suche nach Telefonnummer                           
            "$KOMMANDO4" ) suche_telefon
                           ;;
                           
            # Suche nach Name
            "$KOMMANDO5" ) suche_name
                           ;;
                           
            # Eintrag loeschen
            "$KOMMANDO6" ) loeschen
                           ;;
                           
            # Ende
            "$KOMMANDO7" ) echo -e "\nBeendet."
                           exit
                           ;;
        esac
    done
}

# --------------------------------------------------------- #
# daten_einlesen()                                          #
# Daten fuer neuen Datensatz einlesen und ggf. speichern    #
# --------------------------------------------------------- #
daten_einlesen()
{
    echo -e "\n\033[42mNeuen Eintrag erstellen\033[0m\n"

    # Alle Daten muessen eingegeben werden, deshalb per while
    # auf Eingabe warten
    while [ -z "$VORNAME" ]
    do
        read -e -p "Vorname: " VORNAME
    done
    
    while [ -z "$NACHNAME" ]
    do
        read -e -p "Nachname: " NACHNAME
    done

    while [ -z "$STRASSE" ]
    do   
        read -e -p "Strasse Hausnummer: " STRASSE
    done
    
    while [ -z "$ORT" ]
    do
        read -e -p "PLZ Ort: " ORT
    done
    
    while [ -z "$TELEFON" ]
    do
        read -e -p "Telefonnummer: " TELEFON
    done
   
    if grep -iq "$TELEFON" "$DATA_FILE"
    then
        echo -e "\nFehler, Datensatz mit Telefonnummer $TELEFON ist bereits vorhanden!\n"
        return 1;
    fi
    
    echo -e "\n\033[41mVorschau des neuen Eintrages:\033[0m\n"
    echo -e "$VORNAME $NACHNAME, $STRASSE, $ORT, $TELEFON\n"
    read -e -p "Eintrag speichern? [J | n] " SPEICHERN

    if [ "$SPEICHERN" = "n" ]
    then
        echo -e "Abbruch, nicht gespeichert.\n"
        unset VORNAME NACHNAME STRASSE ORT TELEFON
    else
        echo "Speichere Datensatz ... "
        datensatz_speichern "$VORNAME" "$NACHNAME" "$STRASSE" "$ORT" "$TELEFON"
        echo -e "Erfolreich gespeichert.\n"
    fi

    return 0
}

# --------------------------------------------------------- #
# datensatz_speichern                                       #
# Datensatz an Datei anhaengen                              #
# --------------------------------------------------------- #
datensatz_speichern()
{
    # Neue Zeile einfach an Datei anhaengen
    echo "$1;$2;$3;$4;$5" >> "$DATA_FILE"
}

# --------------------------------------------------------- #
# ausgabe_liste()                                           #
# Liste der Adressen ausgeben                               #
# --------------------------------------------------------- #
ausgabe_liste()
{
    echo -e "\033[42m"
    printf "%-11s%-15s%-15s%-20s%-15s" "Name" "Vorname" "Strasse" "Ort" "Telefon"
    echo -e "\033[0m\n"
   
    # Wenn Argument vorhanden, geben wir die Liste sortiert aus
    if [ ! -z $1 ]
    then
        while read NACHNAME VORNAME STRASSE ORT TELEFON
        do
            printf "%-10s%-15s%-15s%-20s%-15s\n" $NACHNAME \
                                                 $VORNAME \
                                                 $STRASSE \
                                                 $ORT \
                                                 $TELEFON
        # sortieren nach $1. Feld, dann 2. Feld
        done < $DATA_FILE | sort -t \; -k "$1" -k 2 -f   
    else
        while read NACHNAME VORNAME STRASSE ORT TELEFON
        do
            printf "%-10s%-15s%-15s%-20s%-15s\n" $NACHNAME \
                                                 $VORNAME \
                                                 $STRASSE \
                                                 $ORT \
                                                 $TELEFON
        done < $DATA_FILE
    fi      
    
    echo -e "\n"
}

# --------------------------------------------------------- #
# suche_name()                                              #
# Suche nach Datensatz anhand des Namens                    #
# --------------------------------------------------------- #
suche_name()
{
    echo -e "\n\033[42mNach Name suchen\033[0m\n"
    
    while [ -z "$NAME" -a "$NAME" != "0" ]
    do
        read -e -p "Teil des Namens eingeben [0 = Abbruch]: " NAME
    done

    # Abbrechen
    if [ "$NAME" == "0" ]
    then
        unset SUCH_ERGEBNIS NAME
        return 0;
    fi    
    
    if SUCH_ERGEBNIS=`grep -Fi "$NAME" "$DATA_FILE"`
    then
        echo -e "\n\033[42mSuchtreffer\033[0m\n"
        echo "$SUCH_ERGEBNIS" | awk 'BEGIN { FS=";[ ]*"} 
                                           { printf "\033[43m%s %s\033[0m: %s\n", $1, $2, $5 }'
        echo -e "\n"
    else
        echo -e "\n\033[41mKeine Suchergebnisse\033[0m\n"
    fi

    unset SUCH_ERGEBNIS NAME
}

# --------------------------------------------------------- #
# suche_telefon()                                           #
# Suche nach Datensatz anhand der Telefonnummer             #
# --------------------------------------------------------- #
suche_telefon()
{
    echo -e "\n\033[42mNach Telefonnummer suchen\033[0m\n"

    while [ -z "$TELEFON" -a "$TELEFON" != "q" ]
    do
        read -e -p "Telefonnummer [q = Abbruch]: " TELEFON
    done
 
    # Abbrechen
    if [ "$TELEFON" == "q" ]
    then
        unset SUCH_ERGEBNIS TELEFON
        return 0;
    fi
    
    # Datei per grep durchsuchen 
    if SUCH_ERGEBNIS=`grep "$TELEFON" "$DATA_FILE"`
    then
        echo -e "\n\033[42mSuchtreffer\033[0m\n"
        echo "$SUCH_ERGEBNIS" | awk 'BEGIN { FS=";[ ]*"} 
                                           { printf "%s %s: \033[43m%s\033[0m\n", $1, $2, $5 }'
        echo -e "\n"
    else
        echo -e "\n\033[41mKeine Suchergebnisse\033[0m\n"
    fi

    unset SUCH_ERGEBNIS TELEFON
}

# --------------------------------------------------------- #
# loeschen()                                                #
# Datensatz loeschen                                        #
# --------------------------------------------------------- #
loeschen()
{
    echo -e "\n\033[42mEintrag loeschen\033[0m\n"
    
    while [ -z "$VORNAME" ]
    do
        read -e -p "Vorname exakt eingeben: " VORNAME
    done
    
    while [ -z "$NACHNAME" ]
    do
        read -e -p "Nachname exakt eingeben: " NACHNAME
    done    
   
    # Nur wenn Datensazu vorhanden, koennen wir ihn auch loeschen 
    if grep -iq "$VORNAME;$NACHNAME" "$DATA_FILE"
    then
        # Neuen Inhalt in Datei schreiben
        NEUER_INHALT=`grep -vi "$VORNAME;$NACHNAME" "$DATA_FILE"`
        echo "$NEUER_INHALT" > "$DATA_FILE"
        echo -e "\n\033[42mDatensatz geloescht\033[0m\n"
    else
        echo -e "\n\033[41mDatensatz ist nicht vorhanden!\033[0m\n"
    fi

    unset NEUER_INHALT VORNAME NACHNAME
}

setup;
main;

exit 0;
