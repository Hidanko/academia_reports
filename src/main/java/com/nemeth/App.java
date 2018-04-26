package com.nemeth;

import static net.sf.dynamicreports.report.builder.DynamicReports.cht;
import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

import com.nemeth.templates.Templates;

import net.sf.dynamicreports.report.builder.chart.LineChartBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;


public class App {
	public static void main(String[] args) throws DRException {
		TextColumnBuilder<String> colunaData = col.column("Data", "data", type.stringType());
		TextColumnBuilder<String> colunaPorcentagem = col.column("Data", "data", type.stringType());

		LineChartBuilder lineChart = cht.lineChart()

				.setShowPercentages(true)

				.setShowValues(true)
				.setDataSource(createDatasource())
				.setCategory(colunaPorcentagem)
		// .setSeriesOrderBy(seriesOrderBy)
		// .series(cht.serie(colunaData))
		;

		report().setTemplate(Templates.reportTemplate)
				.addTitle(Templates.createTitleComponent("RELATÓRIO \n " + "NOME_DO_ALUNO", "ACADEMIA DO BUGHI",
						"aula.com", "TEXTO_01", "TEXTO_02"))

				.addTitle(cmp.horizontalList(cmp.text("Codigo: ").setStyle(Templates.boldStyle),
						cmp.text("CODIGO_DO_ALUNO"), cmp.text(""), cmp.text("")))
				.addTitle(cmp.horizontalList(cmp.text("Treinador: ").setStyle(Templates.boldStyle),
						cmp.text("NOME_DO_PERSONAL"), cmp.text(""), cmp.text("")))
				.addTitle(cmp.verticalList(cmp.text(""),
						cmp.text("Relatório do dia " + "DATA_INICIAL" + " até o dia " + "DATA_FINAL" + ".")
								.setStyle(Templates.bold12CenteredStyle)))
				
				.addTitle(lineChart)
				
				.addPageFooter(Templates.footerComponent,
						cmp.text("TEXTO_MOTIVACIONAL").setStyle(Templates.rodapeDireita))
				.show();
	}

	private static DRDataSource createDatasource() {
		DRDataSource lista = new DRDataSource("id", "dia", "porcentagem");

		lista.add(1, 02 / 02, 80);
		lista.add(2, 03 / 02, 88);
		lista.add(3, 04 / 02, 100);
		lista.add(4, 06 / 02, 100);
		lista.add(5, 07 / 02, 70);
		lista.add(6, 10 / 02, 90);

		return lista;
	}
}
