/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

Highcharts.chart('container2', {
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
        text: 'Experiencias y actividades'
    },
    subtitle: {
        text: 'Experiencias y actividades'
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
            text: 'Numero de actividades',
            margin: 20
        }
    },
    tooltip: {
        valueSuffix: ' actividades'
    },
    series: [{
        name: 'Actividades',
        data: datos2
    }]
});

