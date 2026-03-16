import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { StudentService, Student } from '../services/student';
import { FormsModule } from '@angular/forms'; // ✅ Import FormsModule

@Component({
  selector: 'app-edit-student',
  templateUrl: './edit-student.html',
  styleUrls: ['./edit-student.css'],
  standalone: true,
  imports: [FormsModule] // ✅ Needed for ngModel
})
export class EditStudentComponent implements OnInit {
  student: Student = { regNo: '', rollNo: '', name: '', standard: '', school: '' };

  constructor(
    private studentService: StudentService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const regNo = this.route.snapshot.paramMap.get('regNo');
    if (regNo) {
      const s = this.studentService.getStudentByRegNo(regNo);
      if (s) this.student = s;
    }
  }

  updateStudent() {
    this.studentService.updateStudent(this.student);
    this.router.navigate(['/students']);
  }
}