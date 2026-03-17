import { Component } from '@angular/core';
import { StudentService, Student } from '../../services/student';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-student-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './student-form.html'
})
export class StudentFormComponent {

  student: Student = {
    regNo: 0,
    rollNo: 0,
    name: '',
    standard: 0,
    school: '',
    gender: 'MALE',
    percentage: 0
  };

  constructor(private studentService: StudentService) {}

  onSubmit() {
    this.studentService.addStudent(this.student).subscribe(() => {
      alert("Student added successfully!");
      this.student = {regNo:0, rollNo:0, name:'', standard:0, school:'', gender:'MALE', percentage:0};
    });
  }
}