using System;
using System.ServiceModel;
using System.ServiceModel.Web;
using RestService;

class Client {
  static void Main() {
    Users list;
    int index;

    RestProxy proxy = new RestProxy();
    list = proxy.GetUsers();
    Console.WriteLine("Initial users:");
    foreach (User usr in list)
      Console.WriteLine("Id: {0}    Name: {1}", usr.UserId, usr.Name);
    index = proxy.AddUser("Mary Jane");
    Console.WriteLine("\nAdd user nr. {0}", index);
    list = proxy.GetUsers();
    Console.WriteLine("\nUser list:");
    foreach (User usr in list)
      Console.WriteLine("Id: {0}    Name: {1}", usr.UserId, usr.Name);
    Console.WriteLine("\nDelete user nr. 1");
    proxy.DeleteUser("1");
    list = proxy.GetUsers();
    Console.WriteLine("\nUser list:");
    foreach (User usr in list)
      Console.WriteLine("Id: {0}    Name: {1}", usr.UserId, usr.Name);
    Console.WriteLine("\nChange user nr. 2");
    proxy.ChangeUser("2", "Peter Smith");
    list = proxy.GetUsers();
    Console.WriteLine("\nUser list:");
    foreach (User usr in list)
      Console.WriteLine("Id: {0}    Name: {1}", usr.UserId, usr.Name);
    proxy.Close();
    Console.ReadKey(true);
  }
}

class RestProxy : ClientBase<IRestService>, IRestService {
  public Users GetUsers() {
    return Channel.GetUsers();
  }

  public User GetUser(string id) {
    return Channel.GetUser(id);
  }

  public int AddUser(string name) {
    return Channel.AddUser(name);
  }

  public void DeleteUser(string id) {
    Channel.DeleteUser(id);
  }

  public void ChangeUser(string id, string newname) {
    Channel.ChangeUser(id, newname);
  }
}