import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StudentService, Student } from '../../services/student';
import { Observable } from 'rxjs'; // Import this

@Component({
  selector: 'app-student-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './student-list.html',
  styleUrls: ['./student-list.css']
})
export class StudentListComponent implements OnInit {
  // 1. Change type to Observable
  students$!: Observable<Student[]>; 

  constructor(private studentService: StudentService) {}

  ngOnInit(): void {
    this.loadStudents();
  }

  loadStudents() {
    console.log("Fetching students...");
    // 2. Just assign the stream, don't .subscribe() manually
    this.students$ = this.studentService.getAllStudents();
  }

  deleteStudent(regNo: number) {
    if(confirm("Are you sure?")){
      this.studentService.deleteStudent(regNo).subscribe(() => {
        this.loadStudents(); // Refresh the observable
      });
    }
  }
}