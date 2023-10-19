<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="text"/>
    <xsl:template match="/">
        <xsl:text>ID_ART,NAME,CODE,USERNAME,GUID</xsl:text>
        <xsl:value-of select="'&#xa;&#xa;'"/>
            <xsl:for-each select="articles/article">
                <xsl:value-of select="id_art" />
                <xsl:value-of select="','" />

                <xsl:value-of select="name" />
                <xsl:value-of select="','" />

                <xsl:value-of select="code" />
                <xsl:value-of select="','" />

                <xsl:value-of select="username" />
                <xsl:value-of select="','" />

                <xsl:value-of select="guid" />
                <xsl:value-of select="'&#xa;'"/>

            </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>
