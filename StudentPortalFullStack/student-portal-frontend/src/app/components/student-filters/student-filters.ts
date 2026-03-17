import { Component, ChangeDetectorRef } from '@angular/core';
import { StudentService, Student } from '../../services/student';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-student-filters',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './student-filters.html'
})
export class StudentFiltersComponent {
  // Separate variables to prevent "mirroring"
  schoolSearch: string = '';   // For the list
  schoolCountName: string = ''; // For the school count
  stdCountNum: number = 0;     // For the standard count
  
  // Shared variables (used in the bottom sections)
  gender: string = 'MALE';
  standardNum: number = 0;
  pass: boolean = true;

  filteredStudents: Student[] = [];
  schoolCount: number | null = null;
  standardCount: number | null = null;
  strength: number | null = null;
  resultStudents: Student[] = [];

  constructor(private studentService: StudentService, private cdr: ChangeDetectorRef) {}

  filterBySchool() {
    this.studentService.getBySchool(this.schoolSearch).subscribe(data => {
      this.filteredStudents = data;
      this.cdr.detectChanges();
    });
  }

  getSchoolCount() {
    this.studentService.countBySchool(this.schoolCountName).subscribe(count => {
      this.schoolCount = count;
      this.cdr.detectChanges();
    });
  }

  getStandardCount() {
    this.studentService.countByStandard(this.stdCountNum).subscribe(count => {
      this.standardCount = count;
      this.cdr.detectChanges();
    });
  }

  getStrength() {
    this.studentService.getStrength(this.gender, this.standardNum).subscribe(count => {
      this.strength = count;
      this.cdr.detectChanges();
    });
  }

  getResults() {
    this.studentService.getResult(this.pass).subscribe(data => {
      this.resultStudents = data;
      this.cdr.detectChanges();
    });
  }
}