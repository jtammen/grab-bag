/*
 * Doppelt verkettete Liste.
 *
 * Jan Tammen, os4
 */

#include <stdio.h>
#include <malloc.h>

typedef struct node {
    int data;
    struct node *next, *prev;
} node;

int addAfter(node*, int, node*);
int addBefore(node*, int, node*);
int delete(node*, int);
void traverse(node*, unsigned int);

int main ()
{
    struct node* head = (struct node*) malloc(sizeof(struct node));
    head->data = 0;
    head->prev = NULL;
    head->next = NULL;
    
    while (1)
    {
	int action = 6;
	
        printf("Enter your choice:\n");
        printf("\t 1) Insert node after\n");
        printf("\t 2) Insert node before\n");
        printf("\t 3) Delete node\n");
        printf("\t 4) Traverse list using next\n");
        printf("\t 5) Traverse list using prev\n");
        printf("\t 6) Exit\n");
        printf(">>");
	scanf("%d", &action);

	int pos, val;
	
	switch (action)
	{
	    case 1:
		printf("Enter p: "); scanf("%d", &pos);
    		printf("Enter integer: "); scanf("%d", &val);

		// Neuen Knoten erstellen
		struct node* newnode_after = (struct node*) malloc(sizeof(struct node));
		newnode_after->data = val;
		
		// Knoten einhaengen
		if (addAfter(head, pos, newnode_after) == -1)
		    printf("Error: Cannot insert.");

		break;

	    case 2:
		printf("Enter p: "); scanf("%d", &pos);
    		printf("Enter integer: "); scanf("%d", &val);

		// Neuen Knoten erstellen
		struct node* newnode_before = (struct node*) malloc(sizeof(struct node));
		newnode_before->data = val;
		
		// Knoten einhaengen
		if (addBefore(head, pos, newnode_before) == -1)
		    printf("Error: Cannot insert.");
	
		break;

	    case 3:
		printf("Enter p: "); scanf("%d", &pos);
		
		// Knoten loeschen
		if (delete(head, pos) == -1)
		    printf("Error: Cannot delete.");
	
		break;

	    case 4:
		printf("The list is ");
		traverse(head, 0);  // 0 == next
		break;

	    case 5:
		printf("The list is ");
		traverse(head, 1);  // 1 == prev
		break;
		
	    case 6:
		return 0;
	}

	printf("\n\n");
    } // while
    
    return 0;
}

// Neuen Knoten nach Knoten an Position pos einfuegen
int addAfter(node* head, int pos, node* neu)
{
    if (pos < 0) return -1;
  
    // Richtige Position finden
    int i = 0;
    for (i = 0; i < pos; ++i)
    {
	head = head->next;
	if (head == NULL) return -1;	// Listenende
    }

    // Head sollte nun auf den Knoten zeigen, NACH welchem
    // wir unseren neuen Knoten einfuegen wollen
    
    // Also neuen Knoten einhaengen
    neu->next = head->next;
    neu->prev = head;
    if (neu->next) neu->next->prev = neu;
    head->next = neu;
    
    return 0;
}

// Neuen Knoten vor Knoten an Position pos einfuegen
int addBefore(node* head, int pos, node* neu)
{
    if (pos <= 0) return -1;
  
    // Richtige Position finden
    int i = 0;
    for (i = 0; i < pos; ++i)
    {
	head = head->next;
	if (head == NULL) return -1;	// Listenende
    }

    // Head sollte nun auf den Knoten zeigen, VOR welchem
    // wir unseren neuen Knoten einfuegen wollen
    
    // Also neuen Knoten einhaengen
    neu->prev = head->prev;
    neu->next = head;
    if (head->prev) head->prev->next = neu;
    head->prev = neu;
    
    return 0;
}

// Knoten an Position pos loeschen
int delete(node* head, int pos)
{
    if (pos <= 0) return -1;
    
    // Richtige Position finden
    int i = 0;
    for (i = 0; i < pos; ++i)
    {
	head = head->next;
	if (head == NULL) return -1;	// Listenende
    }

    // Head sollte nun auf den Knoten zeigen, welchen
    // wir loeschen wollen

    // Also die anderen Knoten umhaengen
    if (head->prev) head->prev->next = head->next;
    if (head->next) head->next->prev = head->prev;

    // Ausgabe machen
    printf("Integer %i is deleted.", head->data);

    // Speicher freigeben. Korrekt so!?
    free(head);
    
    return 0;
}

// Liste traversieren, 0 = vorwaerts, 1 = rueckwaerts
void traverse(node* start, unsigned int mode)
{
    if (start->next == NULL && start->prev == NULL) return;

    switch (mode)
    {
	case 0:
	    // Liste vorwaerts durchlaufen
	    while ( 1 )
	    {
		start = start->next;
		if (start == NULL) break;
		printf("%i", start->data);
		if (start->next != NULL) printf(",");
	    }
	    break;

	case 1:
	    // Liste zunaechst bis zum Ende durchlaufen...
	    while ( 1 ) 
	    { 
		start = start->next;
		if (start->next == NULL) break;
	    }

	    // ... und dann rueckwaerts ausgeben
	    while ( 1 )
	    {
		if (start->prev == NULL) break;
		printf("%i", start->data);
		if (start->prev->prev != NULL) printf(",");
		start = start->prev;
	    }
	    break;
    }

    return;
}
