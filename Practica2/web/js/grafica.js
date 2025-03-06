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
        text: 'Salarios de los empleados'
    },
    subtitle: {
        text: 'Empresa2024'
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
                fontSize: '14px'
            }
        }
    },
    yAxis: {
        title: {
            text: 'Salario en euros',
            margin: 20
        }
    },
    tooltip: {
        valueSuffix: ' euros'
    },
    series: [{
        name: 'Salario',
        data: datos
    }]
});
