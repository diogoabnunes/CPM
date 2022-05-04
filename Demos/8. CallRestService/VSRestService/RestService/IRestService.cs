using System.Collections.Generic;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.ComponentModel;

namespace RestService {
  [ServiceContract]
  public interface IRestService {
    [WebGet(UriTemplate="/users", ResponseFormat=WebMessageFormat.Json)]
    [Description("Gets all users stored so far.")]
    [OperationContract]
    Users GetUsers();

    [WebInvoke(Method = "POST", UriTemplate = "/users", RequestFormat = WebMessageFormat.Json, ResponseFormat = WebMessageFormat.Json)]
    [Description("Adds one user.")]
    [OperationContract]
    int AddUser(string name);

    [WebInvoke(Method="GET", UriTemplate="/users/{id}", ResponseFormat=WebMessageFormat.Json)]
    [Description("Gets one user by id.")]
    [OperationContract]
    User GetUser(string id);

    [WebInvoke(Method="DELETE", UriTemplate="/users/{id}")]
    [Description("Deletes one user by id.")]
    [OperationContract]
    void DeleteUser(string id);
    
    [WebInvoke(Method="PUT", UriTemplate="/users/{id}", RequestFormat=WebMessageFormat.Json)]
    [Description("Changes the name of an existing user by id.")]
    [OperationContract]
    void ChangeUser(string id, string newname);
  }

  [CollectionDataContract(Name="users", Namespace="")]
  public class Users : List<User> {
  }

  [DataContract(Name="user", Namespace="")]
  public class User {
    [DataMember(Name="id", Order=1)]
    public int UserId { get; set; }

    [DataMember(Name="name", Order=2)]
    public string Name { get; set; }
  }
}