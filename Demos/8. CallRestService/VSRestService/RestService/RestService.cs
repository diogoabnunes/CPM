using System;
using System.Net;
using System.ServiceModel.Web;

namespace RestService {
  public class RestService : IRestService {
    static Users users = null;
    static int index = 0;

    public RestService() {
      GenerateUsers();
    }

    public Users GetUsers() {
      Console.WriteLine("In GetUsers()");
      return users;
    }

    public int AddUser(string name) {
      Console.WriteLine("In AddUser() name = " + name);
      User user = new User() { UserId = ++index, Name = name };
      users.Add(user);
      return index;
    }

    public User GetUser(string id) {
      User user = null;
      int uid = Convert.ToInt32(id);
      bool found = false;

      Console.WriteLine("In GetUser() uid = " + id);

      foreach (User usr in users) {
        if (usr.UserId == uid) {
          user = usr;
          found = true;
          break;
        }
      }
      if (!found) {
        WebOperationContext ctx = WebOperationContext.Current;
        ctx.OutgoingResponse.StatusCode = HttpStatusCode.NotFound;
      }
      return user;
    }

    public void DeleteUser(string id) {
      int uid = Convert.ToInt32(id);
      bool removed = false;

      Console.WriteLine("In DeleteUser() uid = " + id);

      foreach (User usr in users)
        if (usr.UserId == uid) {
          users.Remove(usr);
          removed = true;
          break;
        }
      if (!removed) {
        WebOperationContext ctx = WebOperationContext.Current;
        ctx.OutgoingResponse.StatusCode = HttpStatusCode.NotFound;
      }
    }

    public void ChangeUser(string id, string newname) {
      int uid = Convert.ToInt32(id);
      bool changed = false;

      Console.WriteLine("In ChangeUser() uid = " + id + "  new name = " + newname);

      foreach (User usr in users) {
        if (usr.UserId == uid) {
          usr.Name = newname;
          changed = true;
          break;
        }
      }
      if (!changed) {
        WebOperationContext ctx = WebOperationContext.Current;
        ctx.OutgoingResponse.StatusCode = HttpStatusCode.NotFound;
      }
    }

    private void GenerateUsers() {
      if (users == null) {
        users = new Users();
        users.Add(new User() { UserId = 1, Name = "John Doe" });
        users.Add(new User() { UserId = 2, Name = "Jane Doe" });
        index = 2;
      }
    }

  }
}