import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  login(username: string, password: string): boolean {
    if (username === 'admin' && password === 'admin123') {
      localStorage.setItem('role', 'ADMIN');
      return true;
    } else if (username === 'staff' && password === 'staff123') {
      localStorage.setItem('role', 'STAFF');
      return true;
    }
    return false;
  }

  logout() {
    localStorage.removeItem('role');
  }

  getRole(): string | null {
    return localStorage.getItem('role');
  }

  isAdmin(): boolean {
    return this.getRole() === 'ADMIN';
  }

  isStaff(): boolean {
    return this.getRole() === 'STAFF';
  }
}