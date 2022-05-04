using System;
using System.ServiceModel.Web;

class RestHost {
  static void Main() {
    WebServiceHost host = new WebServiceHost(typeof(RestService.RestService));
    host.Open();
    Console.WriteLine("Rest service running");
    Console.WriteLine("Press ENTER to stop the service");
    Console.ReadLine();
    host.Close();
  }
}