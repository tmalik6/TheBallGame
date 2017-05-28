package prt.theballgame;
//CHECKSTYLE:OFF

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/*
 * #%L
 * TheBallGame
 * %%
 * Copyright (C) 2016 Debreceni Egyetem, Informatikai Kar
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */
public class GameXML {

    public static int ido = BallSettings.getSecoundspassed();
    public static int KorokSzama = BallSettings.getKorSzama();
    public static int radius = BallSettings.getRadius();
    public static boolean fulldisplay = BallSettings.isFulldisplay();
    public static int idomentes;
    public static int Kormentes;
    public static int radiusmentes;
    public static boolean fulldisplaymentes;
    public static boolean jobberedmeny;
    public static boolean letezik;

    public static void Save() throws TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e1) {
            e1.printStackTrace();
        }
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = tFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            Document doc = builder.newDocument();
            DOMSource source = new DOMSource(doc);
            ido = BallSettings.getSecoundspassed();
            KorokSzama = BallSettings.getKorSzama();
            radius = BallSettings.getRadius();
            fulldisplay = BallSettings.isFulldisplay();
            File f = new File("./bests.xml");
            if (f.exists() && !f.isDirectory()) {
                StreamResult file = new StreamResult("./bests.xml");
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                Load();
                if (BallSettings.pontszam(ido, KorokSzama, fulldisplay) > BallSettings.pontszam(Kormentes, idomentes, fulldisplaymentes)) {
                    jobberedmeny = true;
                    Kormentes = KorokSzama;
                    idomentes = ido;
                    radiusmentes = radius;
                    fulldisplaymentes = fulldisplay;
                    try {
                        Element rootElement = doc.createElement("HighScore");
                        doc.appendChild(rootElement);

                        Integer e1 = Kormentes;
                        Element Circlenumber = doc.createElement("Circlenumber");
                        Circlenumber.appendChild(doc.createTextNode(e1.toString()));
                        rootElement.appendChild(Circlenumber);

                        Integer e2 = idomentes;
                        Element Time = doc.createElement("Time");
                        Time.appendChild(doc.createTextNode(e2.toString()));
                        rootElement.appendChild(Time);

                        Integer e3 = radiusmentes;
                        Element Radiusnumber = doc.createElement("Radiusnumber");
                        Radiusnumber.appendChild(doc.createTextNode(e3.toString()));
                        rootElement.appendChild(Radiusnumber);

                        Boolean e4 = fulldisplaymentes;
                        Element Fulldisplay = doc.createElement("Fulldisplay");
                        Fulldisplay.appendChild(doc.createTextNode(e4.toString()));
                        rootElement.appendChild(Fulldisplay);

                        transformer.transform(source, file);
                    } catch (TransformerException e) {
                        e.printStackTrace();
                    }
                } else {
                    jobberedmeny = false;
                }
            } else {
                StreamResult file = new StreamResult(new File("./bests.xml"));

                jobberedmeny = true;
                Kormentes = KorokSzama;
                idomentes = ido;
                radiusmentes = radius;
                fulldisplaymentes = fulldisplay;
                Element rootElement = doc.createElement("HighScore");
                doc.appendChild(rootElement);

                Integer e1 = Kormentes;
                Element Circlenumber = doc.createElement("Circlenumber");
                Circlenumber.appendChild(doc.createTextNode(e1.toString()));
                rootElement.appendChild(Circlenumber);

                Integer e2 = idomentes;
                Element Time = doc.createElement("Time");
                Time.appendChild(doc.createTextNode(e2.toString()));
                rootElement.appendChild(Time);

                Integer e3 = radiusmentes;
                Element Radiusnumber = doc.createElement("Radiusnumber");
                Radiusnumber.appendChild(doc.createTextNode(e3.toString()));
                rootElement.appendChild(Radiusnumber);

                Boolean e4 = fulldisplaymentes;
                Element Fulldisplay = doc.createElement("Fulldisplay");
                Fulldisplay.appendChild(doc.createTextNode(e4.toString()));
                rootElement.appendChild(Fulldisplay);

                transformer.transform(source, file);
            }
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void Load() {

        File file = new File("./bests.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        if (file.exists() && !file.isDirectory()) {
            letezik = true;
            try {
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document document = db.parse(file);
                Kormentes = Integer.parseInt(document.getElementsByTagName("Circlenumber").item(0).getTextContent());
                idomentes = Integer.parseInt(document.getElementsByTagName("Time").item(0).getTextContent());
                radiusmentes = Integer.parseInt(document.getElementsByTagName("Radiusnumber").item(0).getTextContent());
                fulldisplaymentes = Boolean.valueOf(document.getElementsByTagName("Fulldisplay").item(0).getTextContent());
            } catch (ParserConfigurationException | SAXException | IOException e1) {
                e1.printStackTrace();
            }
        } else {
            letezik = false;
        }
    }
}
