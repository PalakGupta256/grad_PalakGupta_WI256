import { Component, OnInit } from '@angular/core';
import { StudentService, Student } from '../services/student';
import { AuthService } from '../services/auth';
import { RouterLink } from '@angular/router';
import { NgIf, NgFor } from '@angular/common'; // ✅ Import structural directives

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.html',
  styleUrls: ['./student-list.css'],
  standalone: true,
  imports: [RouterLink, NgIf, NgFor] // ✅ Include NgIf and NgFor
})
export class StudentListComponent implements OnInit {
  students: Student[] = [];

  constructor(private studentService: StudentService, public auth: AuthService) {}

  ngOnInit(): void {
    this.students = this.studentService.getStudents();
  }

  deleteStudent(regNo: string) {
    if (confirm('Are you sure?')) {
      this.studentService.deleteStudent(regNo);
      this.students = this.studentService.getStudents();
    }
  }
}