@AfterStep({ config.afterSteps ? (context.step in config.afterSteps) : true })
void after(context) {
  println "Splunk: running after the ${context.library} library's ${context.step} step"
}
