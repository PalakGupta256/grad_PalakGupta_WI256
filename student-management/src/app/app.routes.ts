import { Routes } from '@angular/router';
import { LoginComponent } from './login/login';
import { StudentListComponent } from './student-list/student-list';
import { AddStudentComponent } from './add-student/add-student';
import { EditStudentComponent } from './edit-student/edit-student';
import { AdminGuard } from './guards/admin-guard';

export const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'students', component: StudentListComponent },
  { path: 'add', component: AddStudentComponent, canActivate: [AdminGuard] },
  { path: 'edit/:regNo', component: EditStudentComponent, canActivate: [AdminGuard] }
];