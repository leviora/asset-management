import React from "react";
import {
  Container,
  Typography,
  Grid,
  Card,
  CardContent,
  Box,
  Button,
  AppBar,
  Toolbar
} from "@mui/material";

function HomePage() {

  return React.createElement(

    React.Fragment,
    null,

    // HEADER
    React.createElement(
      AppBar,
      { position: "static", color: "primary" },

      React.createElement(
        Toolbar,
        null,

        React.createElement(
          Typography,
          { variant: "h6", sx: { flexGrow: 1 } },
          "School Asset Management"
        ),

        React.createElement(Button,{color:"inherit"},"Dashboard"),
        React.createElement(Button,{color:"inherit"},"Assets"),
        React.createElement(Button,{color:"inherit"},"Rooms"),
        React.createElement(Button,{color:"inherit"},"Assignments")
      )
    ),

    // CONTENT
    React.createElement(
      Container,
      {
        maxWidth: "xl",
        sx: { marginTop: 4, marginBottom: 6 }
      },

      // TITLE
      React.createElement(
        Box,
        { sx: { marginBottom: 3 } },

        React.createElement(
          Typography,
          { variant: "h4", gutterBottom: true },
          "Dashboard"
        ),

        React.createElement(
          Typography,
          { color: "text.secondary" },
          "Overview of the school asset management system"
        )
      ),

      // STATISTICS
      React.createElement(
        Grid,
        { container: true, spacing: 2 },

        [
          {title:"Assets",value:"124",color:"#7c4dff"},
          {title:"Rooms",value:"18",color:"#9575CD"},
          {title:"Unassigned",value:"7",color:"#FF9800"},
          {title:"Repairs",value:"3",color:"#E91E63"}
        ].map((item,index)=>

          React.createElement(
            Grid,
            { item:true, xs:12, md:3, key:index },

            React.createElement(
              Card,
              {
                sx:{
                  height:110,
                  borderRadius:3,
                  boxShadow:2,
                  position:"relative",
                  transition:"0.2s",
                  "&:hover":{
                    transform:"translateY(-3px)",
                    boxShadow:4
                  }
                }
              },

              // AKCENT KOLORU
              React.createElement(
                Box,
                {
                  sx:{
                    height:6,
                    backgroundColor:item.color,
                    borderTopLeftRadius:12,
                    borderTopRightRadius:12
                  }
                }
              ),

              React.createElement(
                CardContent,
                null,

                React.createElement(
                  Typography,
                  {variant:"h5"},
                  item.value
                ),

                React.createElement(
                  Typography,
                  {color:"text.secondary"},
                  item.title
                )
              )
            )
          )
        )
      ),

      // QUICK ACTIONS
      React.createElement(
        Box,
        { sx:{marginTop:4,marginBottom:2} },

        React.createElement(
          Typography,
          {variant:"h6"},
          "Quick Actions"
        )
      ),

      React.createElement(
        Grid,
        { container:true, spacing:2 },

        ["Add Asset","Add Room","Assign Asset"].map((title,index)=>

          React.createElement(
            Grid,
            { item:true, xs:12, md:4, key:index },

            React.createElement(
              Card,
              {
                sx:{
                  height:130,
                  borderRadius:3,
                  boxShadow:2,
                  transition:"0.2s",
                  "&:hover":{
                    transform:"translateY(-3px)",
                    boxShadow:4
                  }
                }
              },

              React.createElement(
                CardContent,
                null,

                React.createElement(
                  Typography,
                  {variant:"h6",gutterBottom:true},
                  title
                ),

                React.createElement(
                  Button,
                  {variant:"contained",color:"primary"},
                  "Open"
                )
              )
            )
          )
        )
      )
    ),

    // FOOTER
    React.createElement(
      Box,
      {
        sx:{
          textAlign:"center",
          padding:2,
          backgroundColor:"#f5f5f5"
        }
      },

      React.createElement(
        Typography,
        {variant:"body2",color:"text.secondary"},
        "School Asset Management System"
      )
    )

  );
}

export default HomePage;