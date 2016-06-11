// Copyright (c) 2012-2016 Saarland University
// All rights reserved.
//
// Author: Konrad Jamrozik, jamrozik@st.cs.uni-saarland.de
//
// This file is part of the "DroidMate" project.
//
// www.droidmate.org

package org.droidmate.plugin_hook;

import android.content.Context;

// !!!!! =================================================================
// !!!!! DO NOT EDIT THIS FILE !!!
// !!!!! =================================================================
// !!!!! Instead, run full gradle rebuild (see README.md) or do:
// !!!!! 
// !!!!!   cd repo/droidmate/dev/droidmate 
// !!!!!   ./gradlew :projects:plugin-api-hook:compileJava 
// !!!!! 
// !!!!! This will generate HookPlugin.java in the same directory as this class. Edit that file instead.

@SuppressWarnings({"unused","Duplicates"})
public class HookPluginTemplate implements IHookPlugin
{
  // KJA add "init hook method" and pass Context to it instead of before/after. Probably call it from org.droidmate.monitor_template_src.MonitorJavaTemplate.init 
  // KJA add "finalize hook method". Probably called somewhere from org.droidmate.device.MonitorsClient
  // KJA add a dependency on a way to destructure apilogcatmessagePayload: org.droidmate.common.logcat.ApiLogcatMessage.from(java.lang.String)
  public void hookBeforeApiCall(Context context, String apiLogcatMessagePayload)
  {
    // exampleHookBefore(context, apiLogcatMessagePayload);
  }

  private void exampleHookBefore(Context context, String apiLogcatMessagePayload)
  {
    System.out.println("hookBeforeApiCall/apiLogcatMessagePayload: "+ apiLogcatMessagePayload);
  }

  public Object hookAfterApiCall(Context context, String apiLogcatMessagePayload, Object returnValue)
  {
    return returnValue;
    // return exampleHookAfter(apiLogcatMessagePayload, returnValue);
  }

  private Object exampleHookAfter(String apiLogcatMessagePayload, Object returnValue)
  {
    System.out.println("hookAfterApiCall/returnValue: "+ returnValue);
    if (apiLogcatMessagePayload.contains("mthd: getDeviceId"))
    {
      String mockedDevId = "DEV-ID-MOCKED-BY-AFTER-HOOK";
      System.out.println("hookAfterApiCall: replacing deviceId="+returnValue+" with mocked value: "+mockedDevId);
        
      return mockedDevId;
    }
    else
      return returnValue;
  }
}
