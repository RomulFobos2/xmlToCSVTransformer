package com.tander.xmlToCSVTransformer.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement()
public class Article {
    @XmlAttribute(name = "id_art")
    private String idArt;

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "code")
    private String code;

    @XmlAttribute(name = "username")
    private String username;

    @XmlAttribute(name = "guid")
    private String guid;
}
