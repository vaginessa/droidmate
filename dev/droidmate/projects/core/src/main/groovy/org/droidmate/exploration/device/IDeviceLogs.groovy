// Copyright (c) 2012-2015 Saarland University
// All rights reserved.
//
// Author: Konrad Jamrozik, jamrozik@st.cs.uni-saarland.de
//
// This file is part of the "DroidMate" project.
//
// www.droidmate.org
package org.droidmate.exploration.device

import org.droidmate.logcat.IApiLogcatMessage
import org.droidmate.logcat.ITimeFormattedLogcatMessage

import java.time.LocalDateTime

interface IDeviceLogs
{
  boolean getContainsMonitorInitTime()

  LocalDateTime getMonitorInitTime()

  LocalDateTime getMonitorInitTimeOrNull()

  List<ITimeFormattedLogcatMessage> getInstrumentationMsgs()

  List<ITimeFormattedLogcatMessage> getInstrumentationMsgsOrNull()

  List<IApiLogcatMessage> getApiLogs()

  List<IApiLogcatMessage> getApiLogsOrEmpty()

  boolean getReadAnyApiLogsSuccessfully()

  void setMonitorInitTime(LocalDateTime time)

  void setInstrumentationMsgs(List<ITimeFormattedLogcatMessage> messages)

  void setApiLogs(List<IApiLogcatMessage> apiLogs)
}