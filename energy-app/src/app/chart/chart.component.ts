import { Component, OnInit } from '@angular/core';
import { AppComponent } from '../app.component';
import { Router } from '@angular/router';
import { MeasurementService } from '../core/measurement/measurement.service';
import Chart from 'chart.js/auto';
import { Measurement } from '../core/measurement/measurement';



@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.css']
})
export class ChartComponent implements OnInit  {
  deviceID : number | undefined;
  public chart: any;

  measurements : Measurement[] | undefined;
  hours: (number | undefined)[] | undefined;
  values : (number | undefined)[] | undefined;

  //filtered measurements
  filteredMeasurements : Measurement[] | undefined;
  filteredHours: (number | undefined)[] | undefined;
  filteredValues : (number | undefined)[] | undefined;

  selectedDate: Date | null = null;

  constructor(private measurementService : MeasurementService, private router: Router, public myapp: AppComponent)  {
    
  }
  
  ngOnInit(): void {
    this.deviceID = Number(localStorage.getItem('deviceID'));
    this.measurementService.getDeviceMeasurements(this.deviceID).subscribe(data => {
      this.measurements = data;
    
    });

  }

  private getMeasurements(deviceID : number) {
    this.measurementService.getDeviceMeasurements(deviceID).subscribe(data => {
      this.measurements = data;
      // this.getHours();
      // this.getValues();
    });
  }


  private getFilteredHours() {
    if(this.filteredMeasurements!=undefined) {
      this.filteredHours = this.filteredMeasurements.map(measurement => measurement.hour);
    }
  }

  private getFilteredValues() {
    if(this.filteredMeasurements!=undefined) {
      this.filteredValues = this.filteredMeasurements.map(measurement => measurement.value);
    }
  }
  
  formatDate(date: Date): string {
    if (!isNaN(date.getTime())) {
      const year = date.getFullYear();
      const month = (date.getMonth() + 1).toString().padStart(2, '0');
      const day = date.getDate().toString().padStart(2, '0');
  
      return `${year}-${month}-${day}`;
    }
  
    return ''; // Returnează un șir gol în cazul în care conversia la Date a eșuat
  }
  


  filterMeasurementsByDate() {
    if (this.selectedDate && this.measurements) {
      const dateString = this.formatDate(this.selectedDate);
      this.filteredMeasurements = this.measurements.filter((measurement: Measurement) => {
        if (measurement.measurementDate !== undefined) {
          const measurementDateObject = new Date(measurement.measurementDate);
          console.log("date string[", dateString, "]se compara cu data[",measurementDateObject.toISOString().split('T')[0], "]");
          
          if (!isNaN(measurementDateObject.getTime())) {
            return measurementDateObject.toISOString().split('T')[0] === dateString;
          }
        }
        return false;
      });
    } else {
 
      this.filteredMeasurements = [];
    }

    this.getFilteredHours();
    this.getFilteredValues();
    this.createChart();
  }

  createChart(){
   if (this.chart) {
      this.chart.data.labels = this.filteredHours;
      this.chart.data.datasets[0].label = 'Hourly measurements';
      this.chart.data.datasets[0].data = this.filteredValues;
      this.chart.data.datasets[0].backgroundColor = 'red';
      this.chart.update();
    } else {
      this.chart = new Chart('MyChart', {
        type: 'line',
        data: {
          labels: this.filteredHours,
          datasets: [
            {
              label: 'Hourly measurements',
              data: this.filteredValues,
              backgroundColor: 'green'
            }
          ]
        },
        options: {
          aspectRatio: 2.5,
        }
      });
    }
  }

}
