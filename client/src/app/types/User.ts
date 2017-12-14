export class Role {
  static GUEST: string = "GUEST";
  static USER: string = "USER";
  static ADMIN: string = "ADMIN";
}

export class User {
  username: string;
  password: string;
  role: string;
  id: number;

  constructor(username?: string, password?: string, email?: string, role?: string, id?: number) {
    this.username = username || "";
    this.password = password || "";
    this.role = role || Role.GUEST;
    this.id = id || -1;
  }
}
