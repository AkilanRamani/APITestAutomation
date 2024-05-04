package APITestAutomation;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.jdom2.Attribute;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadXMLFileExample1 {

	Document doc;
	File file;

	@BeforeTest
	public void beforeTest() throws ParserConfigurationException, SAXException, IOException {
		file = new File(
				"C:\\Users\\akilan-18633\\eclipse-workspace\\APITestAutomation\\src\\test\\resources\\SchemaValidation\\data.xml");
		// an instance of factory that gives a document builder
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		// an instance of builder to parse the specified xml file
		DocumentBuilder db = dbf.newDocumentBuilder();
		doc = db.parse(file);
		doc.getDocumentElement().normalize(); // Top Most Root elemnet in the XML.

	}

	@Test
	public void readMetaDataValue() {

		System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
		NodeList nodeList = doc.getElementsByTagName("metadata");
		// nodeList is not iterable, so we are using for loop
		for (int itr = 0; itr < nodeList.getLength(); itr++) {
			Node node = nodeList.item(itr);
			System.out.println("\nNode Name :" + node.getNodeName());
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) node;
				System.out.println(
						"Student id: " + eElement.getElementsByTagName("Retail_Outlet_Id").item(0).getTextContent());
				System.out.println(
						"First Name: " + eElement.getElementsByTagName("TransactionID").item(0).getTextContent());
				System.out.println(
						"Last Name: " + eElement.getElementsByTagName("TransactionType").item(0).getTextContent());
				System.out.println("Subject: " + eElement.getElementsByTagName("FileID").item(0).getTextContent());
				System.out.println("Marks: " + eElement.getElementsByTagName("XMLCRDate").item(0).getTextContent());
			}
		}
	}

	@Test
	public void readCompanyDetails() {
		NodeList nodeList = doc.getElementsByTagName("CompanyDetails");
		// nodeList is not iterable, so we are using for loop
		for (int itr = 0; itr < nodeList.getLength(); itr++) {
			Node node = nodeList.item(itr);
			System.out.println("\nNode Name :" + node.getNodeName());
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) node;
				System.out.println("Student id: " + eElement.getElementsByTagName("Name").item(0).getTextContent());
				System.out.println("First Name: " + eElement.getElementsByTagName("Street").item(0).getTextContent());
				System.out.println("Last Name: " + eElement.getElementsByTagName("Address").item(0).getTextContent());
				System.out.println("Subject: " + eElement.getElementsByTagName("Place").item(0).getTextContent());
				System.out.println("Marks: " + eElement.getElementsByTagName("LCode").item(0).getTextContent());
				System.out.println("Subject: " + eElement.getElementsByTagName("OUTLETID").item(0).getTextContent());

			}
		}
	}

	@Test
	public void readingIndividualAttributeValues() {
		NodeList nodeList = doc.getElementsByTagName("OUTLET_STO_HDR");
		for (int i = 0; i < nodeList.getLength(); i++) { // i<2
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {

				Element nodeElement = (Element) node;
				System.out.println("Node value: " + nodeElement.getAttribute("MSH_DN_DATE"));

			}

		}
	}

	@Test
	public void readingAttributeValues() {
		NodeList nodeList = doc.getElementsByTagName("metadata");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {

				NodeList nodeDetails = node.getChildNodes();
				for (int j = 0; j < nodeDetails.getLength(); j++) {
					Node detail = nodeDetails.item(j);
					if (detail.getNodeType() == Node.ELEMENT_NODE) {
						Element detailElement = (Element) detail;
						System.out
								.println("     " + detailElement.getTagName() + ": " + detailElement.getTextContent());
					}

				}

			}
		}

	}

	@Test
	public void validatingThePresenceOfTextValueInXmlNode() {
		String key;
		NodeList nl = doc.getElementsByTagName("OUTLET_STO_HDR");
		for (int i = 0; i < nl.getLength(); i++) {
			Node currentItem = nl.item(i);
			key = currentItem.getAttributes().getNamedItem("MSH_DN_AMOUNT").getNodeValue();
			if (key == null) {
				System.out.println("The XML Node Does Not have any Node Value Asssociated with it");
			} else {
				System.out.println(key);
			}

		}

	}

	@Test
	public void validatingThePresenceOfTextValueInXmlNode1() {
		NodeList nodeList = doc.getElementsByTagName("OUTLET_STO_HDR");

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);

			if (node.hasAttributes()) {
				Attr attr = (Attr) node.getAttributes().getNamedItem("MSH_DN_AMOUNT");
				if (attr != null) {
					String attribute = attr.getValue();
					System.out.println("attribute: " + attribute);
				} else {
					System.out.println("Attribute does not have any value ");
				}
			}
		}
	}

	@Test
	public void fetchingTheRootNodeType() {
		String rootNodeName = doc.getDocumentElement().getNodeName();
		System.out.println(rootNodeName);
		Element rootNodeName1 = doc.getDocumentElement();
		short nodeType = rootNodeName1.getNodeType();
		System.out.println(nodeType);
		if (nodeType == Node.ELEMENT_NODE) {
			System.out.println("The Root Element is an Element Node");
		} else if (nodeType == Node.DOCUMENT_NODE) {
			System.out.println("The Root Element is an Document Node");
		} else if (nodeType == Node.ATTRIBUTE_NODE) {
			System.out.println("The Root Element is an Attribute Node");
		} else if (nodeType == Node.COMMENT_NODE) {
			System.out.println("The Root Element is a comment Node");
		} else if (nodeType == Node.TEXT_NODE) {
			System.out.println("The Root Element is a Text Node");
		}
	}

	@Test
	public void fetchingTheRootNodeName() {
		String rootNodeName = doc.getDocumentElement().getNodeName();
		System.out.println(rootNodeName);
		Element rootNodeName1 = doc.getDocumentElement();
		if (rootNodeName1.hasChildNodes()) {
			NodeList nodeList = rootNodeName1.getChildNodes();
			int childnodecount = nodeList.getLength();
			for (int i = 0; i < childnodecount; i++) {
				if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {

					System.out.println(nodeList.item(i).getNodeName());
				}
			}

		}
	}

	@Test
	public void fetchingTheNodeAndItsTextContext() {
		Element rootElement = doc.getDocumentElement();
		NodeList nodeList = rootElement.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			if (nodeList.item(i).getNodeType() == 1) {
				System.out.println(" " + nodeList.item(i).getNodeName() + " ");
				NodeList nodeListUnderEmployee = nodeList.item(i).getChildNodes();
				for (int j = 0; j < nodeListUnderEmployee.getLength(); j++) {
					if (nodeListUnderEmployee.item(j).getNodeType() == 1) {
						System.out.println(nodeListUnderEmployee.item(j).getNodeName() + " "
								+ nodeListUnderEmployee.item(j).getTextContent());

					}
				}

			}
		}
	}

	@Test
	public void fetchingTheNodeAndItsTextContext1() {
		Element rootElement = doc.getDocumentElement();
		System.out.println(rootElement.getNodeName());
		System.out.println("---");
		if (rootElement.hasChildNodes()) {
			NodeList nodeList = rootElement.getChildNodes();
			int childnodecount = nodeList.getLength();
			for (int i = 0; i < childnodecount; i++) {
				if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {

					System.out.println("-----" + nodeList.item(i).getNodeName() + "-----");
					if (nodeList.item(i).hasChildNodes()) {
						NodeList nodeLists = nodeList.item(i).getChildNodes();
						int childnodecounts = nodeLists.getLength();
						for (int j = 0; j < childnodecounts; j++) {
							if (nodeLists.item(j).getNodeType() == Node.ELEMENT_NODE) {
								String nodeName = nodeLists.item(j).getNodeName();
								String nodevalue = nodeLists.item(j).getTextContent();
								System.out.println(nodeName + "=" + nodevalue);

							}
						}

					}
				}
			}
		}

	}

	@Test
	public void readingNodeValueUsingJDOM() throws JDOMException, IOException {
		SAXBuilder saxBuilder = new SAXBuilder();
		org.jdom2.Document doc = saxBuilder.build(file);
		System.out.println("Root element :" + doc.getRootElement().getName());
		org.jdom2.Element ele = doc.getRootElement();
		java.util.List<org.jdom2.Element> elementList = ele.getChildren("metadata");
		for (org.jdom2.Element emailelement : elementList) {
			System.out.println("Current element::  " + emailelement.getName());
			// Attribute attribute= emailelement.getAttribute("Subject");
			// System.out.println("Subject:: "+attribute.getValue());
			System.out.println("Retail_Outlet_Id ::  " + emailelement.getChild("Retail_Outlet_Id").getText());
			System.out.println("TransactionID::  " + emailelement.getChild("TransactionID").getText());
			System.out.println("TransactionType::  " + emailelement.getChild("TransactionType").getText());
			System.out.println("FileID::  " + emailelement.getChild("FileID").getText());
			System.out.println("XMLCRDate::  " + emailelement.getChild("XMLCRDate").getText());
		}
	}

	@Test
	public void readingHeaderNodeAttributeValuesUsingJDOM() throws JDOMException, IOException {
		SAXBuilder saxBuilder = new SAXBuilder();
		org.jdom2.Document doc = saxBuilder.build(file);
		System.out.println("Root element :" + doc.getRootElement().getName());
		org.jdom2.Element ele = doc.getRootElement();
		java.util.List<org.jdom2.Element> elementList = ele.getChildren("OUTLET_STO_HDR");
		for (org.jdom2.Element emailelement : elementList) {
			System.out.println("Current element::  " + emailelement.getName());
			Attribute attribute = emailelement.getAttribute("MSH_DN_DATE");
			// System.out.println("Subject:: " + attribute);
			// System.out.println("Subject:: " + attribute.getName());
			System.out.println(attribute.getName() + " " + attribute.getValue());
			Attribute attribute1 = emailelement.getAttribute("MSH_DN_DOCT_CODE");
			System.out.println(attribute1.getName() + " " + attribute1.getValue());
			Attribute attribute2 = emailelement.getAttribute("MSH_DN_AMOUNT");
			System.out.println(attribute2.getName() + " " + attribute.getValue());
			Attribute attribute3 = emailelement.getAttribute("MSH_CGST_AMT");
			System.out.println(attribute3.getName() + " " + attribute.getValue());
			Attribute attribute4 = emailelement.getAttribute("MSH_IGST_AMT");
			System.out.println(attribute4.getName() + " " + attribute.getValue());
			Attribute attribute5 = emailelement.getAttribute("MSH_SGST_AMT");
			System.out.println(attribute5.getName() + " " + attribute5.getValue());
			Attribute attribute6 = emailelement.getAttribute("MSH_DN_DIFFAMOUNT");
			System.out.println(attribute6.getName() + " " + attribute5.getValue());
			Attribute attribute7 = emailelement.getAttribute("MSH_DN_NO");
			System.out.println(attribute7.getName() + " " + attribute7.getValue());
			Attribute attribute8 = emailelement.getAttribute("MSH_DELIVERY_CHARGE");
			System.out.println(attribute8.getName() + " " + attribute7.getValue());
			Attribute attribute9 = emailelement.getAttribute("RETAIL_OUTLET_ID");
			System.out.println(attribute9.getName() + " " + attribute9.getValue());
			Attribute attribute10 = emailelement.getAttribute("ERP_REF_NO");
			System.out.println(attribute10.getName() + " " + attribute10.getValue());
			Attribute attribute11 = emailelement.getAttribute("MSH_CESS_AMT");
			System.out.println(attribute11.getName() + " " + attribute11.getValue());
			Attribute attribute12 = emailelement.getAttribute("TAX_CONFIG");
			System.out.println(attribute12.getName() + " " + attribute12.getValue());
			Attribute attribute13 = emailelement.getAttribute("MSH_JOB_ORDER_ID");
			System.out.println(attribute13.getName() + " " + attribute12.getValue());
		}

	}

	@Test
	public void readingDetailNodeAttributeValueUsingJDOM() throws JDOMException, IOException {
		SAXBuilder saxBuilder = new SAXBuilder();
		org.jdom2.Document doc = saxBuilder.build(file);
		System.out.println("Root element :" + doc.getRootElement().getName());
		org.jdom2.Element ele = doc.getRootElement();
		java.util.List<org.jdom2.Element> elementList = ele.getChildren("OUTLET_STO_DTL");
		for (org.jdom2.Element emailelement : elementList) {

			System.out.println("Current element::  " + emailelement.getName());
			Attribute attribute = emailelement.getAttribute("MSD_DN_NO");
			// System.out.println("Subject:: " + attribute);
			// System.out.println("Subject:: " + attribute.getName());
			System.out.println(attribute.getName() + " " + attribute.getValue());
			Attribute attribute1 = emailelement.getAttribute("MSD_ITEM_CODE");
			System.out.println(attribute1.getName() + " " + attribute1.getValue());
			Attribute attribute2 = emailelement.getAttribute("MSD_ITEM_QTY");
			System.out.println(attribute2.getName() + " " + attribute.getValue());
			Attribute attribute3 = emailelement.getAttribute("MSD_ITEM_RATE");
			System.out.println(attribute3.getName() + " " + attribute.getValue());
			Attribute attribute4 = emailelement.getAttribute("MSD_EXP_DT");
			System.out.println(attribute4.getName() + " " + attribute.getValue());
			Attribute attribute5 = emailelement.getAttribute("MSD_ITEM_TAX_AMT");
			System.out.println(attribute5.getName() + " " + attribute5.getValue());
			Attribute attribute6 = emailelement.getAttribute("MSD_ITEM_DISC_PERC");
			System.out.println(attribute6.getName() + " " + attribute5.getValue());
			Attribute attribute7 = emailelement.getAttribute("MSD_MRP");
			System.out.println(attribute7.getName() + " " + attribute7.getValue());
			Attribute attribute8 = emailelement.getAttribute("MSD_SELLING");
			System.out.println(attribute8.getName() + " " + attribute7.getValue());
			Attribute attribute9 = emailelement.getAttribute("MSD_SGST_PERC");
			System.out.println(attribute9.getName() + " " + attribute9.getValue());
			Attribute attribute10 = emailelement.getAttribute("MSD_SGST_AMT");
			System.out.println(attribute10.getName() + " " + attribute10.getValue());
			Attribute attribute11 = emailelement.getAttribute("MSD_CGST_PERC");
			System.out.println(attribute11.getName() + " " + attribute11.getValue());
			Attribute attribute12 = emailelement.getAttribute("MSD_CGST_AMT");
			System.out.println(attribute12.getName() + " " + attribute12.getValue());
			Attribute attribute13 = emailelement.getAttribute("MSD_IGST_PERC");
			System.out.println(attribute13.getName() + " " + attribute13.getValue());
			Attribute attribute14 = emailelement.getAttribute("MSD_IGST_AMT");
			System.out.println(attribute14.getName() + " " + attribute14.getValue());
			Attribute attribute15 = emailelement.getAttribute("MSD_HSN_CODE");
			System.out.println(attribute15.getName() + " " + attribute15.getValue());
			Attribute attribute16 = emailelement.getAttribute("MSD_BARCODE");
			System.out.println(attribute16.getName() + " " + attribute16.getValue());
			Attribute attribute17 = emailelement.getAttribute("MSD_DN_SL_NO");
			System.out.println(attribute17.getName() + " " + attribute17.getValue());
			Attribute attribute18 = emailelement.getAttribute("MSD_ITEM_AMOUNT");
			System.out.println(attribute18.getName() + " " + attribute18.getValue());
			Attribute attribute19 = emailelement.getAttribute("MSD_ITEM_TAX_PERC");
			System.out.println(attribute19.getName() + " " + attribute19.getValue());
			Attribute attribute20 = emailelement.getAttribute("MSD_CONV_TYPE");
			System.out.println(attribute20.getName() + " " + attribute20.getValue());
			Attribute attribute21 = emailelement.getAttribute("MSD_CONV_FACTOR");
			System.out.println(attribute21.getName() + " " + attribute21.getValue());
			Attribute attribute22 = emailelement.getAttribute("MSD_BATCH_NO");
			System.out.println(attribute22.getName() + " " + attribute22.getValue());
			Attribute attribute23 = emailelement.getAttribute("CAT1");
			System.out.println(attribute23.getName() + " " + attribute23.getValue());
			Attribute attribute24 = emailelement.getAttribute("CAT2");
			System.out.println(attribute24.getName() + " " + attribute24.getValue());
			Attribute attribute25 = emailelement.getAttribute("CAT3");
			System.out.println(attribute25.getName() + " " + attribute25.getValue());
			Attribute attribute26 = emailelement.getAttribute("CAT4");
			System.out.println(attribute26.getName() + " " + attribute26.getValue());
			Attribute attribute27 = emailelement.getAttribute("CAT5");
			System.out.println(attribute27.getName() + " " + attribute27.getValue());
			Attribute attribute28 = emailelement.getAttribute("CAT6");
			System.out.println(attribute28.getName() + " " + attribute28.getValue());
			Attribute attribute29 = emailelement.getAttribute("CAT7");
			System.out.println(attribute29.getName() + " " + attribute29.getValue());
			Attribute attribute30 = emailelement.getAttribute("CAT8");
			System.out.println(attribute30.getName() + " " + attribute30.getValue());
			Attribute attribute31 = emailelement.getAttribute("CAT9");
			System.out.println(attribute31.getName() + " " + attribute31.getValue());
			Attribute attribute32 = emailelement.getAttribute("CAT10");
			System.out.println(attribute32.getName() + " " + attribute32.getValue());
			Attribute attribute33 = emailelement.getAttribute("MSD_LANDING_COST");
			System.out.println(attribute33.getName() + " " + attribute33.getValue());
			Attribute attribute34 = emailelement.getAttribute("MSD_COST_WITHOUT_TAX");
			System.out.println(attribute34.getName() + " " + attribute34.getValue());
			Attribute attribute35 = emailelement.getAttribute("MAIN_DIST_CODE");
			System.out.println(attribute35.getName() + " " + attribute35.getValue());

		}
	}

	@Test
	public void readingNodeValueUsingXpath() throws JDOMException, IOException, XPathExpressionException {
		XPath xPath = XPathFactory.newInstance().newXPath();
		// x-path class which is For Compiling and evaluating the Current X-path that
		// Refers to the Current Element From the DOM.
		String expression = "/OUTLET_STO_PROCESS/metadata"; // x-path
		NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node nNode = nodeList.item(i);
			System.out.println("\nCurrent Element :" + nNode.getNodeName());
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				System.out.println("Retail_Outlet_Id : "
						+ eElement.getElementsByTagName("Retail_Outlet_Id").item(0).getTextContent());
				System.out.println(
						"TransactionID : " + eElement.getElementsByTagName("TransactionID").item(0).getTextContent());
				System.out.println("TransactionType : "
						+ eElement.getElementsByTagName("TransactionType").item(0).getTextContent());
				System.out.println("FileID : " + eElement.getElementsByTagName("FileID").item(0).getTextContent());
				System.out
						.println("XMLCRDate : " + eElement.getElementsByTagName("XMLCRDate").item(0).getTextContent());
			}
		}
	}

	@Test
	public void readingNodeValueUsingXpath1() throws JDOMException, IOException, XPathExpressionException {
		XPath xPath = XPathFactory.newInstance().newXPath();
		// x-path class which is For Compiling and evaluating the Current X-path that
		// Refers to the Current Element From the DOM.
		String expression = "/OUTLET_STO_PROCESS/OUTLET_STO_HDR"; // x-path
		NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node nNode = nodeList.item(i);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				System.out.println(eElement.getTagName());
				String attr = eElement.getAttribute("MSH_DN_DATE");
				System.out.println(attr);

			}
		}
	}
}
