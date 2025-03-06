Highcharts.chart('container', {
    chart: {
        type: 'column',
        options3d: {
            enabled: true,
            alpha: 10,
            beta: 25,
            depth: 70
        }
    },
    title: {
        text: 'Experiencias de los usuarios'
    },
    subtitle: {
        text: 'Experiencia Viaje'
    },
    plotOptions: {
        column: {
            depth: 25
        }
    },
    xAxis: {
        type: 'category',
        labels: {
            skew3d: true,
            style: {
                fontSize: '20px'
            }
        }
    },
    yAxis: {
        title: {
            text: 'Numero de experiencias',
            margin: 20
        }
    },
    tooltip: {
        valueSuffix: ' experiencia'
    },
    series: [{
        name: 'Experiencias',
        data: datos
    }]
});
