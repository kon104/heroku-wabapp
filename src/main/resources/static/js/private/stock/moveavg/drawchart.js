
// {{{ function loadChart(code, url)
function loadChart(code, url) {
	var query = new google.visualization.Query(url);
	query.send(function(response) {
		var data = response.getDataTable();
		drawChart(data, code);
	});
}
// }}}

// {{{ function drawChart(data, code)
function drawChart(data, code) {

	let idDashboard = 'dboard' + code;
	let idCandle = 'candle' + code;
	let idControl = 'ctrl' + code;

	var dashboard = new google.visualization.Dashboard(document.getElementById(idDashboard));
	var chart = new google.visualization.ChartWrapper({
//		'chartType': 'CandlestickChart',
		'chartType': 'ComboChart',
		'containerId': idCandle,
		'options': {
			'title': code,
			'chartArea': {'width': '85%', 'height': '80%'},
			'legend': {'position': 'top'},
			'hAxis': {format: "yyyy/MM/dd"},
			'seriesType': 'candlesticks',
			'series': {
			    1: {'type': 'line', color: 'green'},
			    2: {'type': 'line', color: 'deepskyblue'},
			    3: {'type': 'line', color: 'deeppink'},
			}
		}
	});
	var control = new google.visualization.ControlWrapper({
		'controlType': 'ChartRangeFilter',
		'containerId': idControl,
		'options': {
			'filterColumnIndex': 0,
			'ui': {
				'chartType': 'LineChart',
				'chartOptions': {
					'chartArea': {'width': '85%', 'height': '85%'},
					'hAxis': {format: "yyyy/MM/dd"}
				},
				'chartView': {
					'columns': [0, 2]
				}
			}
		}
	});
	dashboard.bind(control, chart);
	dashboard.draw(data);
}
// }}}

