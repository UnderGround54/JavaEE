<%@ page language = "java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*,java.sql.*" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.google.gson.JsonObject" %>
<%

Gson gsonObj = new Gson();
Map<Object,Object> map = null;
List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
String dataPoints = null;


	try
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection	conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XE","system","prosper 54");
		Statement statement = conn.createStatement();
		
		ResultSet resultat = statement.executeQuery("SELECT COUNT(x) AS y , x FROM DEMANDE GROUP BY x");
		while(resultat.next())
		{
			int	vx = resultat.getInt("x");
			int vy = resultat.getInt("y");
			map = new HashMap<Object,Object>();
			map.put("x",vx);
			map.put("y",vy);
			list.add(map);	
			dataPoints = gsonObj.toJson(list);
		}
		statement.close();	
	}
	catch(ClassNotFoundException ex)
	{
		System.out.println("Probleme de pilote base de données");
		System.out.println(ex.getMessage());
	}
	catch(SQLException ex)
	{
		
		out.println("<div> probleme de connextion </div>");
		System.out.println("Probleme de connexion ou de requetes");
		dataPoints = null;
		System.out.println(ex.getMessage());
	}

%>
<script type="text/javascript">
	window.onload = function(){
		<% if(dataPoints != null){ %>
		 var chart = new CanvasJS.Chart("chartContainer",{
			 
			 animationEnabled:true,
			 exportEnabled : true,
			 title :{
				 text:"Nombre de demande par ans"
			 },
			 axisX:{
				 title : "Années",
				 valueFormatString:"####",
				 interval : 1 
			 },
			 axisY:{
				 title : "Nombre de demande",
				 interval : 1 
			 },
			 data:[{
				 type:"line",
				 toolTipContent : "<b>nombre demande : {y}</b>",
				 dataPoints :<%out.print(dataPoints);%>
			 }]
		 });
		 chart.render();
		<%}%>
	}
	
</script>
			<div class="col-lg-8">
			<section class="panel">
              <div class="panel-body progress-panel">
                <div class="row">
                  <div class="col-lg-8 task-progress pull-left">
                    <h1>GRAPHE</h1>
                  </div>
                </div>
              </div>   
              	<div id="chartContainer" style="heigth:370px; width=100%;"></div> 	 
            </section>
			</div>


<script src="<c:url value="style/js/canvasjs.min.js" />"type="text/javascript"></script>
