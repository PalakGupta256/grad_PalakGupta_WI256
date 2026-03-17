import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StudentListComponent } from './components/student-list/student-list';
import { StudentFormComponent } from './components/student-form/student-form';
import { StudentFiltersComponent } from './components/student-filters/student-filters';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, StudentListComponent, StudentFormComponent, StudentFiltersComponent],
  templateUrl: './app.html',
  styleUrls: ['./app.css']
})
export class App {
  activeTab: string = 'list';

  onStudentAdded() {
    this.activeTab = 'list'; // Switch back to list after adding
  }
}