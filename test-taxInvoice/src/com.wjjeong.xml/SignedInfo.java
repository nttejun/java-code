package com.wjjeong.xml;

import com.sun.org.apache.xml.internal.security.c14n.Canonicalizer;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.security.MessageDigest;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class SignedInfo {

    private Document makeDocument() {
        com.sun.org.apache.xml.internal.security.Init.init();
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            return documentBuilder.newDocument();
        } catch (Exception e) {
            return null;
        }
    }

    public Document makeInputStreamDocument(String filePath) {
        com.sun.org.apache.xml.internal.security.Init.init();
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            InputStream inputStream = new FileInputStream(new File(filePath));
            return documentBuilderFactory.newDocumentBuilder().parse(inputStream);
        } catch (Exception e) {
            return null;
        }
    }

    public String makeDocumentToString(Document document) {
        try {
            StringWriter stringWriter = new StringWriter();
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "false");
            transformer.transform(new DOMSource(document), new StreamResult(stringWriter));
            return stringWriter.toString();
        } catch (Exception e) {
            return null;
        }
    }

    @Test
    public void TEST_MAKE_DIGEST() {
        String text = "가나다라마바사아자차카타파하abcdefghijklmnop";
        try {
            MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
            mDigest.update(text.getBytes());
            byte[] bytesDigest = mDigest.digest();

            StringBuffer hexString = new StringBuffer();

            for (int i = 0;i<bytesDigest.length;i++) {
                String hex = Integer.toHexString(0xff & bytesDigest[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            assertEquals("85fc3f00ec218f7f9f68ce49865b1894d511194b1dff192155fb715808109668", hexString.toString());

        } catch (Exception e){

        }


    }

    @Test
    public void TEST_CREATE_XML() {

        try {
            Document document = makeDocument();
            Element signedInfo = document.createElementNS("http://www.w3.org/2000/09/xmldsig#", "SignedInfo");
            signedInfo.setPrefix("ds");
            signedInfo.setAttribute("xmlns", "urn:kr:.....test......1:0");
            signedInfo.setAttribute("xmlns:ds", "urn:kr:.....test......1:0");
            signedInfo.setAttribute("xmlns:xsi", "urn:kr:.....test......1:0");

            Element canonicalizationMethod = document.createElementNS("https://sample/namespaceURL", "CanonicalizationMethod");
            canonicalizationMethod.setPrefix("ds");
            canonicalizationMethod.setAttribute("Algorithm", "https://test/algorithm");
            signedInfo.appendChild(canonicalizationMethod);

            Canonicalizer canonicalizer = Canonicalizer.getInstance("http://www.w3.org/TR/2001/REC-xml-c14n-20010315");

            String output = new String(canonicalizer.canonicalizeSubtree(signedInfo));
            assertEquals(output, "<ds:SignedInfo xmlns=\"urn:kr:.....test......1:0\" xmlns:ds=\"urn:kr:.....test......1:0\" xmlns:xsi=\"urn:kr:.....test......1:0\"><ds:CanonicalizationMethod Algorithm=\"https://test/algorithm\"></ds:CanonicalizationMethod></ds:SignedInfo>");

        } catch (Exception e) {
        }
    }

    @Test
    public void TEST_LOAD_XML() {

        SignedInfo signedInfo = new SignedInfo();
        Document document = signedInfo.makeInputStreamDocument("/Users/wjjeong/wjjeong/dev/project/java-code/test-taxInvoice/src/com.wjjeong.xml/original.xml");
        assertEquals(signedInfo.makeDocumentToString(document), "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><TaxInvoice xmlns=\"urn:kr:or:kec:standard:Tax:ReusableAggregateBusinessInformationEntitySchemaModule:1:0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"urn:kr:or:kec:standard:Tax:ReusableAggregateBusinessInformationEntitySchemaModule:1:0 http://www.kec.or.kr/standard/Tax/TaxInvoiceSchemaModule_1.0.xsd\">\n" +
                "    <ExchangedDocument>\n" +
                "        <ID/>\n" +
                "        <IssueDateTime>20190814240000</IssueDateTime>\n" +
                "    </ExchangedDocument>\n" +
                "    <TaxInvoiceDocument>\n" +
                "        <IssueID>201908014100003350060463</IssueID>\n" +
                "        <TypeCode>0101</TypeCode>\n" +
                "        <DescriptionText/>\n" +
                "        <IssueDateTime>20190808</IssueDateTime>\n" +
                "        <PurposeCode>02</PurposeCode>\n" +
                "    </TaxInvoiceDocument>\n" +
                "    <TaxInvoiceTradeSettlement>\n" +
                "        <InvoicerParty>\n" +
                "            <ID>00000</ID>\n" +
                "            <TypeCode>TEST</TypeCode>\n" +
                "            <NameText>상호명</NameText>\n" +
                "            <ClassificationCode>업종명</ClassificationCode>\n" +
                "            <SpecifiedPerson>\n" +
                "                <NameText>대표자명</NameText>\n" +
                "            </SpecifiedPerson>\n" +
                "            <DefinedContact>\n" +
                "                <DepartmentNameText>TEST</DepartmentNameText>\n" +
                "                <PersonNameText>담당자명</PersonNameText>\n" +
                "                <TelephoneCommunication>000-000-0000</TelephoneCommunication>\n" +
                "                <URICommunication>TEST@TEST.co.kr</URICommunication>\n" +
                "            </DefinedContact>\n" +
                "            <SpecifiedAddress>\n" +
                "                <LineOneText>TEST주소</LineOneText>\n" +
                "            </SpecifiedAddress>\n" +
                "        </InvoicerParty>\n" +
                "        <InvoiceeParty>\n" +
                "            <ID>000000000</ID>\n" +
                "            <TypeCode>TEST</TypeCode>\n" +
                "            <NameText>CORPORATION</NameText>\n" +
                "            <ClassificationCode>CORPPART</ClassificationCode>\n" +
                "            <SpecifiedOrganization>\n" +
                "                <BusinessTypeCode>01</BusinessTypeCode>\n" +
                "            </SpecifiedOrganization>\n" +
                "            <SpecifiedPerson>\n" +
                "                <NameText>TESTER</NameText>\n" +
                "            </SpecifiedPerson>\n" +
                "            <PrimaryDefinedContact>\n" +
                "                <DepartmentNameText>test</DepartmentNameText>\n" +
                "                <PersonNameText>t100</PersonNameText>\n" +
                "                <TelephoneCommunication>00000000</TelephoneCommunication>\n" +
                "                <URICommunication>test@naver.com</URICommunication>\n" +
                "            </PrimaryDefinedContact>\n" +
                "            <SpecifiedAddress>\n" +
                "                <LineOneText>address</LineOneText>\n" +
                "            </SpecifiedAddress>\n" +
                "        </InvoiceeParty>\n" +
                "        <SpecifiedMonetarySummation>\n" +
                "            <ChargeTotalAmount>0000</ChargeTotalAmount>\n" +
                "            <TaxTotalAmount>000</TaxTotalAmount>\n" +
                "            <GrandTotalAmount>0000</GrandTotalAmount>\n" +
                "        </SpecifiedMonetarySummation>\n" +
                "    </TaxInvoiceTradeSettlement>\n" +
                "    <TaxInvoiceTradeLineItem>\n" +
                "        <SequenceNumeric>1</SequenceNumeric>\n" +
                "        <DescriptionText/>\n" +
                "        <InvoiceAmount>0000</InvoiceAmount>\n" +
                "        <ChargeableUnitQuantity>1</ChargeableUnitQuantity>\n" +
                "        <InformationText/>\n" +
                "        <NameText>PRODUCT</NameText>\n" +
                "        <PurchaseExpiryDateTime>000000</PurchaseExpiryDateTime>\n" +
                "        <TotalTax>\n" +
                "            <CalculatedAmount>000</CalculatedAmount>\n" +
                "        </TotalTax>\n" +
                "        <UnitPrice>\n" +
                "            <UnitAmount>0000</UnitAmount>\n" +
                "        </UnitPrice>\n" +
                "    </TaxInvoiceTradeLineItem>\n" +
                "</TaxInvoice>");

    }

}
