import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { StudentService, Student } from '../services/student';
import { FormsModule } from '@angular/forms'; // ✅ Needed for ngModel

@Component({
  selector: 'app-add-student',
  templateUrl: './add-student.html',
  styleUrls: ['./add-student.css'],
  standalone: true,
  imports: [FormsModule] // ✅ ngModel works
})
export class AddStudentComponent {
  student: Student = { regNo: '', rollNo: '', name: '', standard: '', school: '' };

  constructor(private studentService: StudentService, private router: Router) {}

  addStudent() {
    this.studentService.addStudent(this.student);
    this.router.navigate(['/students']);
  }
}