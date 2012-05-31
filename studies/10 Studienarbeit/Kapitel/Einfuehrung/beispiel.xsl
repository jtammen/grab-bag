<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:transform 
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
  xmlns:html="http://www.w3.org/1999/xhtml"
  xmlns="http://www.w3.org/1999/xhtml"
  version="1.0">

  <xsl:output method="xml" encoding="iso-8859-1"/>

  <xsl:template match="/Studienarbeit">
    <html>
      <head>
        <title>Studienarbeiten</title>
      </head>
      <body>
        <xsl:apply-templates/>
      </body>
    </html>
  </xsl:template>

  <xsl:template match="Thema">
    <h1><xsl:value-of select="."/></h1>
  </xsl:template>
  
  <xsl:template match="Betreuer">
    <p>
      Betreuer: <xsl:value-of select="Titel"/> <xsl:value-of select="@Name"/>
    </p>
  </xsl:template>
  
  <xsl:template match="Autoren">
    <p>Liste der Autoren:
      <ul>
        <xsl:apply-templates select="Autor"/>
      </ul>
    </p>
  </xsl:template>

  <xsl:template match="Autor">
    <li>
      <a>
        <xsl:attribute name="href">
          <xsl:value-of select="Email"/>
        </xsl:attribute>
        
        <xsl:value-of select="@Name"/>
      </a>
    </li>
  </xsl:template>
  
</xsl:transform>
