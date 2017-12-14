export class Routes {
  static LOGIN: string = 'users/login';
  static REGISTER: string = 'users/register';
  static LOGOUT: string = 'users/logout';
  static ROOMS: string = 'rooms';
  static MESSAGES: string = 'messages';
  static USERS: string = 'users';
}

export class ServerRoutes {
  private static address: String = 'localhost';
  private static port: String = '4200';
  private static prefix: String = 'api';

  static routeTo(route: String) {
    return `http://${ServerRoutes.address}:${ServerRoutes.port}/${ServerRoutes.prefix}/${route}`
  }
}


