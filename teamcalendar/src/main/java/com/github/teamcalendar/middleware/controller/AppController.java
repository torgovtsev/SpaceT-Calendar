package com.github.teamcalendar.middleware.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController
{

    @RequestMapping(value = { "/" }, method = { RequestMethod.GET })
    public ModelAndView homePage()
    {
        ModelAndView model = new ModelAndView();
        model.setViewName("homePage");
        return model;
    }

    @RequestMapping(value = { "/admincp/" }, method = { RequestMethod.GET })
    public ModelAndView admincpPage()
    {
        ModelAndView model = new ModelAndView();
        model.setViewName("/admincp/UserList");
        return model;
    }

    @RequestMapping(value = { "/calendar/**" }, method = { RequestMethod.GET })
    public ModelAndView calendarPage()
    {
        ModelAndView model = new ModelAndView();
        model.setViewName("/calendar/tableCalendar");
        return model;
    }

    @RequestMapping(value = { "/login", "/login.xhtml" }, method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout)
    {

        ModelAndView model = new ModelAndView();
        if (error != null)
        {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null)
        {
            model.addObject("logout", "You've been logged out successfully.");
        }

        model.setViewName("login");
        return model;

    }
}
