/* vim: set tabstop=4 shiftwidth=4: */
/*
 * =====================================================================================
 * 
 *        Filename:  Bibliothek.cpp
 * 
 *     Description:  Bibliothek-Klasse Implementierung
 * 
 *         Version:  1.0
 *         Created:  14.10.2004 10:48:21 CEST
 *        Revision:  none
 *        Compiler:  gcc
 * 
 *          Author:  Jan Tammen (jt)
 *         Company:  FH Konstanz
 *           Email:  jan.tammen@fh-konstanz.de
 * 
 * =====================================================================================
 */

#include  "Bibliothek.h"
#include  <iostream>
#include  <iomanip>

/*
 *--------------------------------------------------------------------------------------
 *       Class:  Bibliothek
 *      Method:  Bibliothek
 * Description:  Default-Konstruktor, initialisiert die Member-Variablen
 *--------------------------------------------------------------------------------------
 */
Bibliothek::Bibliothek(void)
{
    this->benutzerListeHead = NULL;
    this->buecherListeHead  = NULL;
}

/*
 *--------------------------------------------------------------------------------------
 *       Class:  Bibliothek
 *      Method:  ~Bibliothek
 * Description:  Destruktor, gibt den belegten Speicher frei
 *--------------------------------------------------------------------------------------
 */
Bibliothek::~Bibliothek(void)
{
    // Benutzer loeschen
    while (this->benutzerListeHead != NULL)
    {
        BenutzerNode* tmpBenutzerListe = this->benutzerListeHead;
        this->benutzerListeHead = tmpBenutzerListe->next;
        delete tmpBenutzerListe->oBenutzer;
        delete tmpBenutzerListe;
    }
    
    // Buecher loeschen
    while (this->buecherListeHead != NULL)
    {
        BuchNode* tmpBuecherListe = this->buecherListeHead;
        this->buecherListeHead = tmpBuecherListe->next;
        delete tmpBuecherListe->oBuch;
        delete tmpBuecherListe;
    }
}

/*
 *--------------------------------------------------------------------------------------
 *       Class:  Bibliothek
 *      Method:  insert
 * Description:  Einen Benutzer einfuegen
 *--------------------------------------------------------------------------------------
 */
bool Bibliothek::insert(std::string name)
{
    if (name.empty())
    {
        std::cerr << "Benutzer kann nicht eingefuegt werden, kein Name uebergeben." << std::endl;
        return false;
    }
    
    // Benutzer-Node erstellen und mit Daten fuellen
    BenutzerNode* newBenutzerNode = new BenutzerNode();

    // Benutzer-Objekt erstellen
    Benutzer* newBenutzer         = new Benutzer(name);

    newBenutzerNode->oBenutzer    = newBenutzer;
    newBenutzerNode->next         = this->benutzerListeHead;

    // Listenanfang auf neue Node setzen    
    this->benutzerListeHead = newBenutzerNode;

    return true;
}

/*
 *--------------------------------------------------------------------------------------
 *       Class:  Bibliothek
 *      Method:  del
 * Description:  Einen Benutzer entfernen
 *--------------------------------------------------------------------------------------
 */
bool Bibliothek::del(std::string name)
{
    if (name.empty())
    {
        std::cerr << "Benutzer kann nicht entfernt werden, kein Name uebergeben." << std::endl;
        return false;
    }
    
    std::cout << "Versuche Benutzer zu loeschen: " << name << std::endl;
    
    // temp. Hilfszeiger anlegen
    BenutzerNode* tmpBenutzerListe = this->benutzerListeHead;
    BenutzerNode* vorgaengerNode   = NULL;
    
    // Benutzerliste durchlaufen
    while (tmpBenutzerListe != NULL && 
           tmpBenutzerListe->oBenutzer->getName() != name)
    {
        // Aktuellen Knoten (also quasi den Vorgaengen) merken
        
        vorgaengerNode   = tmpBenutzerListe;
        tmpBenutzerListe = tmpBenutzerListe->next;
    }
    
    // Liste ist zu Ende, also gab es den Benutzer nicht
    if (tmpBenutzerListe == NULL)
    {
        std::cerr << "Benutzer ist nicht vorhanden: " << name << std::endl;
        return false;
    }
    // Andernfalls: Benutzer aus Liste aushaengen
    else
    {
        // Falls es keinen Vorgaenger gab, war der Benutzer am Anfang der Liste
        if (vorgaengerNode != NULL)
        {
            vorgaengerNode->next    = tmpBenutzerListe->next;
        } else {
            this->benutzerListeHead = tmpBenutzerListe->next;
        }    
    
        delete tmpBenutzerListe->oBenutzer;
        delete tmpBenutzerListe;
        std::cout << "Benutzer erfolgreich entfernt: " << name << std::endl;
    }    
    
    return true;
}

/*
 *--------------------------------------------------------------------------------------
 *       Class:  Bibliothek
 *      Method:  insert
 * Description:  Ein Buch einfuegen
 *--------------------------------------------------------------------------------------
 */
bool Bibliothek::insert(std::string autor, std::string titel)
{
    if (autor.empty() || titel.empty())
    {
        std::cerr << "Buch kann nicht eingefuegt werden, Autor und/oder Titel nicht uebergeben." << std::endl;
        return false;
    }
    
    // Buch-Node erstellen und mit Daten fuellen
    BuchNode* newBuchNode  = new BuchNode();

    // Buch-Objekt erstellen
    Buch* newBuch          = new Buch(autor, titel);

    newBuchNode->oBuch     = newBuch;
    newBuchNode->next      = this->buecherListeHead;

    // Listenanfang auf neue Node setzen    
    this->buecherListeHead = newBuchNode;

    return true;
}

/*
 *--------------------------------------------------------------------------------------
 *       Class:  Bibliothek
 *      Method:  del
 * Description:  Ein Buch entfernen
 *--------------------------------------------------------------------------------------
 */
bool Bibliothek::del(std::string autor, std::string titel)
{
    if (this->buecherListeHead == NULL)
    {
        return false;
    }
    
    if (autor.empty() || titel.empty())
    {
        std::cerr << "Buch kann nicht entfernt werden, kein Autor und/oder Titel uebergeben." << std::endl;
        return false;
    }
    
    std::cout << "Versuche Buch zu loeschen: " << autor << ", " << titel << std::endl;
    
    // temp. Hilfszeiger anlegen
    BuchNode* tmpBuecherListe = this->buecherListeHead;
    BuchNode* vorgaengerNode  = NULL;
    
    // Buecherliste durchlaufen
    while (tmpBuecherListe != NULL && 
           (tmpBuecherListe->oBuch->getAutor() != autor ||
            tmpBuecherListe->oBuch->getTitel() != titel))
    {
        // Aktuellen Knoten (also quasi den Vorgaengen) merken
        vorgaengerNode  = tmpBuecherListe;
        tmpBuecherListe = tmpBuecherListe->next;
    }
    
    // Liste ist zu Ende, also gab es das Buch nicht
    if (tmpBuecherListe == NULL)
    {
        std::cerr << "Buch ist nicht vorhanden: " << autor << ", " << titel << std::endl;
        return false;
    }
    // Andernfalls: Buch aus Liste aushaengen
    else
    {
        // Falls es keinen Vorgaenger gab, war das Buch am Anfang der Liste
        if (vorgaengerNode != NULL)
        {
            vorgaengerNode->next   = tmpBuecherListe->next;
        } else {
            this->buecherListeHead = tmpBuecherListe->next;
        }    
    
        delete tmpBuecherListe->oBuch;
        delete tmpBuecherListe;
        std::cout << "Buch erfolgreich entfernt: " << autor << ", " << titel << std::endl;
    }    
    
    return true;
}

/*
 *--------------------------------------------------------------------------------------
 *       Class:  Bibliothek
 *      Method:  benutzer
 * Description:  Gibt Liste der Benutzer aus
 *--------------------------------------------------------------------------------------
 */
void Bibliothek::benutzer() const
{
    std::cout << "Ausgabe der Benutzerliste: " << std::endl << std::endl;
    
    if (this->benutzerListeHead == NULL)
    {
        std::cout << "Benutzerliste leer." << std::endl;
        return;
    }
    
    
    // temp. Hilfszeiger anlegen
    BenutzerNode* tmpBenutzerListe = this->benutzerListeHead;
    
    while (tmpBenutzerListe != NULL)
    {
        std::cout << tmpBenutzerListe->oBenutzer->getName() << std::endl;
        tmpBenutzerListe = tmpBenutzerListe->next;
    }
    
    std::cout << std::endl;
    
    return;    
}

/*
 *--------------------------------------------------------------------------------------
 *       Class:  Bibliothek
 *      Method:  buecher
 * Description:  Gibt Liste der Buecher aus
 *--------------------------------------------------------------------------------------
 */
void Bibliothek::buecher() const
{
    std::cout << "Ausgabe der Buecherliste: " << std::endl << std::endl;
    
    if (this->buecherListeHead == NULL)
    {
        std::cout << "Buecherliste leer." << std::endl;
        return;
    }
    
    /// temp. Hilfszeiger anlegen
    BuchNode* tmpBuecherListe = this->buecherListeHead;
    
    std::cout << std::left << std::setw(20) << "Autor";
    std::cout << " | ";
    std::cout << std::setw(20) << "Titel" << std::endl;
    std::cout << std::string(40, '-') << std::endl;
        
    while (tmpBuecherListe != NULL)
    {
        std::cout << std::setw(20) << tmpBuecherListe->oBuch->getAutor();
        std::cout << " | ";
        std::cout << std::setw(20) << tmpBuecherListe->oBuch->getTitel() << std::endl;
        tmpBuecherListe = tmpBuecherListe->next;
    }
    
    std::cout << std::endl;
    
    return;
}

/*
 *--------------------------------------------------------------------------------------
 *       Class:  Bibliothek
 *      Method:  ausleihListe
 * Description:  Gibt Liste der ausgeliehenen Buecher eines Nutzers aus
 *--------------------------------------------------------------------------------------
 */
void Bibliothek::ausleihListe(std::string name) const
{
    std::cout << "Ausgabe der Ausleihliste fuer: " << name << std::endl;
    
    Benutzer* oBenutzer = this->sucheBenutzer(name);
    
    if (oBenutzer == NULL)
    {
        std::cerr << "Benutzer wurde nicht gefunden: " << name << std::endl;
        return;
    }
    
    // Entpsrechende Methode des Benutzer-Objekts aufrufen
    oBenutzer->ausleihListe();
    return;
}

/*
 *--------------------------------------------------------------------------------------
 *       Class:  Bibliothek
 *      Method:  ausleihen
 * Description:  Buch zur Ausleih-Liste eines Benutzers hinzufuegen
 *--------------------------------------------------------------------------------------
 */
bool Bibliothek::ausleihen(std::string name, std::string autor, std::string titel)
{
    // Benutzer holen
    Benutzer* oBenutzer = this->sucheBenutzer(name);
    if (oBenutzer == NULL)
    {
        std::cerr << "Benutzer konnte nicht gefunden werden: " << name << std::endl;
        return false;
    }
    
    // Buch holen
    Buch* oBuch = this->sucheBuch(autor, titel);
    if (oBuch == NULL)
    {
        std::cerr << "Buch wurde nicht gefunden: " << autor << ", " << titel << std::endl;
        return false;
    }
    
    // Ist Buch bereits entliehen?
    if (oBuch->getBenutzer() != NULL)
    {
        std::cerr << "Ausleihe fehlgeschlagen, Buch ist bereits entliehen: " << autor << ", " << titel << std::endl;
        return false;
    }
    
    // Ausleihen
    if (oBenutzer->ausleihen(oBuch))
    {
        std::cout << "Ausleihe erfolgreich: " << autor << ", " << titel << ". Benutzer: " << name << std::endl;
        return true;
    }
    
    return false;    
}

/*
 *--------------------------------------------------------------------------------------
 *       Class:  Bibliothek
 *      Method:  ausleihen
 * Description:  Buch aus Ausleih-Liste eines Benutzers entfernen
 *--------------------------------------------------------------------------------------
 */
bool Bibliothek::rueckgabe(std::string autor, std::string titel)
{
    // Buch holen
    Buch* oBuch = this->sucheBuch(autor, titel);
    if (oBuch == NULL)
    {
        std::cerr << "Buch wurde nicht gefunden: " << autor << ", " << titel << std::endl;
        return false;
    }
    
    // Benutzer holen -> ist Buch ueberhaupt entliehen?
    Benutzer* oBenutzer = oBuch->getBenutzer();
    if (oBenutzer == NULL)
    {
        std::cerr << "Rueckgabe fehlgeschlagen, Buch ist nicht entliehen: " 
                  << autor << ", " << titel << std::endl;
        return false;
    }
        
    // Rueckgabe
    if (oBenutzer->rueckgabe(oBuch))
    {
        std::cout << "Rueckgabe erfolgreich: " << autor << ", " << titel 
                  << ". Benutzer: " << oBenutzer->getName() << std::endl;
        return true;
    }
    
    return false;  
}

/*
 *--------------------------------------------------------------------------------------
 *       Class:  Bibliothek
 *      Method:  sucheBenutzer
 * Description:  Benutzer anhand des Namens suchen
 *--------------------------------------------------------------------------------------
 */
Benutzer* Bibliothek::sucheBenutzer(std::string name) const
{    
    // temp. Hilfszeiger anlegen
    BenutzerNode* tmpBenutzerListe = this->benutzerListeHead;
    
    // Benutzerliste durchlaufen
    while (tmpBenutzerListe != NULL && 
           tmpBenutzerListe->oBenutzer->getName() != name)
    {
        tmpBenutzerListe = tmpBenutzerListe->next;
    }
    
    // Liste ist zu Ende, also gab es den Benutzer nicht
    if (tmpBenutzerListe == NULL)
    {
        Benutzer* tmpBenutzer = NULL;
        return tmpBenutzer;
    }
    // Andernfalls: Benutzer aus Liste zurueckgeben
    else
    {
        return tmpBenutzerListe->oBenutzer;
    }
}

/*
 *--------------------------------------------------------------------------------------
 *       Class:  Bibliothek
 *      Method:  sucheBuch
 * Description:  Buch anhand des Autors und Titels suchen
 *--------------------------------------------------------------------------------------
 */
Buch* Bibliothek::sucheBuch(std::string autor, std::string titel) const
{    
    // temp. Hilfszeiger anlegen
    BuchNode* tmpBuecherListe = this->buecherListeHead;
    
    // Benutzerliste durchlaufen
    while (tmpBuecherListe != NULL && 
           (tmpBuecherListe->oBuch->getAutor() != autor ||
            tmpBuecherListe->oBuch->getTitel() != titel))
    {
        tmpBuecherListe = tmpBuecherListe->next;
    }
    
    // Liste ist zu Ende, also gab es den Benutzer nicht
    if (tmpBuecherListe == NULL)
    {
        Buch* tmpBuch = NULL;
        return tmpBuch;
    }
    // Andernfalls: Benutzer aus Liste zurueckgeben
    else
    {
        return tmpBuecherListe->oBuch;
    }
}
