package com.nemeth.templates;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.hyperLink;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;
import static net.sf.dynamicreports.report.builder.DynamicReports.tableOfContentsCustomizer;
import static net.sf.dynamicreports.report.builder.DynamicReports.template;

import java.awt.Color;
import java.util.Locale;

import net.sf.dynamicreports.report.base.expression.AbstractValueFormatter;
import net.sf.dynamicreports.report.builder.ReportTemplateBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.datatype.BigDecimalType;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.builder.tableofcontents.TableOfContentsCustomizerBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.constant.VerticalTextAlignment;
import net.sf.dynamicreports.report.definition.ReportParameters;

public class Templates {

	private static PageType formatoPagina = PageType.A4;
	private static final String logoCabecalho = "resources/images/logo.png";
	private static final int logoCabecalhoX = 60;
	private static final int logoCabecalhoY = 60;
	
	// Informações
	private static String logoRodape = "resources/images/logo02.jpg";
	private static final int logoRodapeX = 130;
	private static final int logoRodapeY = 30;
	private static final String textoRodape = "";

	// Configurações de layout
	public static final StyleBuilder rootStyle;
	public static final StyleBuilder boldStyle;
	public static final StyleBuilder italicStyle;
	public static final StyleBuilder boldCenteredStyle;
	public static final StyleBuilder bold12CenteredStyle;
	public static final StyleBuilder bold18CenteredStyle;
	public static final StyleBuilder bold22CenteredStyle;
	public static final StyleBuilder bold26CenteredStyle;
	public static final StyleBuilder columnStyle;
	public static final StyleBuilder columnTitleStyle;
	public static final StyleBuilder groupStyle;
	public static final StyleBuilder subtotalStyle;
	public static final StyleBuilder centered;
	public static final StyleBuilder invisible;

	public static final StyleBuilder rodapeDireita;
	public static final StyleBuilder boldGroup;

	public static final ReportTemplateBuilder reportTemplate;
	public static final CurrencyType currencyType;
	//public static final ComponentBuilder<?, ?> dynamicReportsComponent;
	public static final ComponentBuilder<?, ?> footerComponent;
	public static final String inteiroSemVirgula;
	
	static {
		rootStyle = stl.style().setPadding(2);
		boldStyle = stl.style(rootStyle).bold();
		italicStyle = stl.style(rootStyle).italic();
		boldCenteredStyle = stl.style(boldStyle).setTextAlignment(HorizontalTextAlignment.CENTER,
				VerticalTextAlignment.MIDDLE);
		bold12CenteredStyle = stl.style(boldCenteredStyle).setFontSize(12);
		bold18CenteredStyle = stl.style(boldCenteredStyle).setFontSize(18);
		bold22CenteredStyle = stl.style(boldCenteredStyle).setFontSize(22);
		bold26CenteredStyle = stl.style(boldCenteredStyle).setFontSize(26);
		columnStyle = stl.style(rootStyle).setVerticalTextAlignment(VerticalTextAlignment.MIDDLE);
		columnTitleStyle = stl.style(columnStyle).setBorder(stl.pen1Point())
				.setHorizontalTextAlignment(HorizontalTextAlignment.CENTER).setBackgroundColor(Color.LIGHT_GRAY).bold();
		groupStyle = stl.style(boldStyle).setHorizontalTextAlignment(HorizontalTextAlignment.LEFT);
		subtotalStyle = stl.style(boldStyle).setTopBorder(stl.pen1Point());
		centered = stl.style().setTextAlignment(HorizontalTextAlignment.CENTER, VerticalTextAlignment.MIDDLE);
		rodapeDireita = stl.style().setTextAlignment(HorizontalTextAlignment.RIGHT, VerticalTextAlignment.TOP);
		boldGroup = stl.style(boldStyle).setTextAlignment(HorizontalTextAlignment.LEFT, VerticalTextAlignment.MIDDLE);
		invisible = stl.style();
		inteiroSemVirgula = "#";

		StyleBuilder crosstabGroupStyle = stl.style(columnTitleStyle);
		StyleBuilder crosstabGroupTotalStyle = stl.style(columnTitleStyle).setBackgroundColor(new Color(170, 170, 170));
		StyleBuilder crosstabGrandTotalStyle = stl.style(columnTitleStyle).setBackgroundColor(new Color(140, 140, 140));
		StyleBuilder crosstabCellStyle = stl.style(columnStyle).setBorder(stl.pen1Point());

		TableOfContentsCustomizerBuilder tableOfContentsCustomizer = tableOfContentsCustomizer().setHeadingStyle(0,
				stl.style(rootStyle).bold());

		reportTemplate = template().setLocale(Locale.ENGLISH).setColumnStyle(columnStyle)
				.setColumnTitleStyle(columnTitleStyle).setGroupStyle(groupStyle).setGroupTitleStyle(groupStyle)
				.setSubtotalStyle(subtotalStyle).highlightDetailEvenRows().crosstabHighlightEvenRows()
				.setCrosstabGroupStyle(crosstabGroupStyle).setCrosstabGroupTotalStyle(crosstabGroupTotalStyle)
				.setCrosstabGrandTotalStyle(crosstabGrandTotalStyle).setCrosstabCellStyle(crosstabCellStyle)
				.setTableOfContentsCustomizer(tableOfContentsCustomizer)
				.setPageFormat(formatoPagina)
				;
		
		currencyType = new CurrencyType();

		
		footerComponent = cmp
				.verticalList(cmp.horizontalList(cmp.text(""),
						// Remover proxima imagem para tirar o logo do rodapé
						cmp.image(logoRodape).setFixedDimension(logoRodapeX, logoRodapeY).setStyle(centered),
						cmp.text(textoRodape).setStyle(rodapeDireita)), cmp.pageXofY())

				.setStyle(

						stl.style(boldCenteredStyle).setTopBorder(stl.pen1Point()));
	}

	public static ComponentBuilder<?, ?> createTitleComponent(String titulo, String empresa, String site, String texto1, String texto2) {
		
		ComponentBuilder<?, ?> headerData = cmp.horizontalList(cmp.image(logoCabecalho).setFixedDimension(logoCabecalhoX, logoCabecalhoY),
				cmp.verticalList(
						cmp.text(empresa).setStyle(bold26CenteredStyle).setHorizontalTextAlignment(
								HorizontalTextAlignment.LEFT),
						cmp.text(site).setStyle(rootStyle).setHyperLink(hyperLink(site)), cmp.text(texto2).setStyle(rootStyle),
						cmp.text(texto1).setStyle(rootStyle)))
				.setFixedWidth(300);


		
		return cmp.horizontalList()
				.add(headerData,
						cmp.text(titulo).setStyle(bold18CenteredStyle)
								.setHorizontalTextAlignment(HorizontalTextAlignment.RIGHT))
				.newRow().add(cmp.line()).newRow().add(cmp.verticalGap(10));
	}

	public static CurrencyValueFormatter createCurrencyValueFormatter(String label) {
		return new CurrencyValueFormatter(label);
	}

	public static class CurrencyType extends BigDecimalType {
		private static final long serialVersionUID = 1L;

		@Override
		public String getPattern() {
			return "$ #,###.00";
		}
	}

	private static class CurrencyValueFormatter extends AbstractValueFormatter<String, Number> {
		private static final long serialVersionUID = 1L;

		private String label;

		public CurrencyValueFormatter(String label) {
			this.label = label;
		}

		public String format(Number value, ReportParameters reportParameters) {
			return label + currencyType.valueToString(value, reportParameters.getLocale());
		}
	}
}
