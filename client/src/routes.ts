export class Routes {
  static LOGIN: String = 'users/login';
  static REGISTER: String = 'users/register';
  static LOGOUT: String = 'users/logout';
}

export class ServerRoutes {
  private static address: String = 'localhost';
  private static port: String = '4200';
  private static prefix: String = 'api';

  static routeTo(route: String) {
    return `http://${ServerRoutes.address}:${ServerRoutes.port}/${ServerRoutes.prefix}/${route}`
  }
}


