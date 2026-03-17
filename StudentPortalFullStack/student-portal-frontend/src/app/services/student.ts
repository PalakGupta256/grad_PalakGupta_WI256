import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Student {
  regNo: number;
  rollNo: number;
  name: string;
  standard: number;
  school: string;
  gender: string;
  percentage: number;
}

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  private apiUrl = "http://localhost:8282/students";

  constructor(private http: HttpClient) {}

  getAllStudents(): Observable<Student[]> {
    return this.http.get<Student[]>(this.apiUrl);
  }

  getStudent(regNo: number): Observable<Student> {
    return this.http.get<Student>(`${this.apiUrl}/${regNo}`);
  }

  addStudent(student: Student): Observable<Student> {
    return this.http.post<Student>(this.apiUrl, student);
  }

  updateStudent(regNo: number, student: Student): Observable<Student> {
    return this.http.put<Student>(`${this.apiUrl}/${regNo}`, student);
  }

  patchStudent(regNo: number, updates: any): Observable<Student> {
    return this.http.patch<Student>(`${this.apiUrl}/${regNo}`, updates);
  }

  deleteStudent(regNo: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${regNo}`);
  }

  getBySchool(name: string): Observable<Student[]> {
    return this.http.get<Student[]>(`${this.apiUrl}/school?name=${name}`);
  }

  countBySchool(name: string): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/school/count?name=${name}`);
  }

  countByStandard(standard: number): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/school/standard/count?class=${standard}`);
  }

  getResult(pass: boolean): Observable<Student[]> {
    return this.http.get<Student[]>(`${this.apiUrl}/result?pass=${pass}`);
  }

  getStrength(gender: string, standard: number): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/strength?gender=${gender}&standard=${standard}`);
  }
}