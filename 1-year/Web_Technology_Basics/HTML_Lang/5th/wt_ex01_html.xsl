<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" encoding="UTF-8" />
<xsl:template match="/"><xsl:apply-templates /></xsl:template>
<xsl:template match="address">
<html><xsl:apply-templates /></html>
</xsl:template>
<xsl:template match="zip|pref|city|section">
<p><xsl:value-of select="." /></p>
</xsl:template>
</xsl:stylesheet>