package wtb.oddsminer.processors;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import wtb.oddsminer.processor.interfaces.IOddsProcessor;

public class OddsProcessor implements IOddsProcessor {

	private static final String	KOUTCOME_ELEMENT		= "outCome";

	private static final String	KBOOKIE_ATTRIBUTE		= "bookie";

	private static final String	KDODDS_SLIP_ATTRIBUTE	= "dodds_bslip";

	private static final String	KWINNER_ATTRIBUTE		= "winner";

	private static final int	KUNDERSCORE_INT			= 95;

	/*
	 * (non-Javadoc)
	 * 
	 * @see wtb.oddsminer.processors.IOddsProcessor#process(java.io.File)
	 */
	@Override
	public Map<String, Map<String, String>> process( File aFile ) {
		Map<String, Map<String, String>> odds = new HashMap<String, Map<String, String>>();

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse( aFile );
			document.normalize();
			NodeList outComeElementList = document.getElementsByTagName( KOUTCOME_ELEMENT );
			for ( int i = 0; i < outComeElementList.getLength(); i++ ) {
				Map<String, String> oddsMap = new HashMap<String, String>();
				Node node = outComeElementList.item( i );
				NodeList oddsElementList = node.getChildNodes();
				for ( int j = 0; j < oddsElementList.getLength(); j++ ) {
					Node oddsNode = oddsElementList.item( j );
					if ( oddsNode.getNodeType() == Node.ELEMENT_NODE ) {
						NamedNodeMap attrMap = oddsNode.getAttributes();
						Node bookieAttrNode = attrMap.getNamedItem( KBOOKIE_ATTRIBUTE );
						// 95 is the unicode int for '_', we get the index of this char and +1 to
						// get chars after
						String bookieString = bookieAttrNode.getNodeValue().substring(
								bookieAttrNode.getNodeValue().indexOf( KUNDERSCORE_INT ) + 1 );
						oddsMap.put( bookieString, oddsElementList.item( j ).getAttributes()
								.getNamedItem( KDODDS_SLIP_ATTRIBUTE ).getNodeValue() );
					}
				}
				odds.put( outComeElementList.item( i ).getAttributes().getNamedItem(
						KWINNER_ATTRIBUTE ).getNodeValue(), oddsMap );
			}
		}
		catch ( ParserConfigurationException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch ( SAXException e ) {
			// TODO sort this out
			e.printStackTrace();
		}
		catch ( IOException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return odds;
	}
}
