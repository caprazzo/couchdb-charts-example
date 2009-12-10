if [[ -n "$TM_SELECTED_FILES" ]]
then
   # THIS WILL RUN JSLINT ON THE SELECTED
   # FILES IN THE PROJECT DRAWER
   eval arr=("$TM_SELECTED_FILES")
   for (( i = 0; i <${#arr[@]}; i++ )); do
	  filePath=${arr[$i]}
	  pathLen=${#filePath}-3
	  pathEnd=${filePath:$pathLen:3}
	  if [ $pathEnd == ".js" ]
		 then
			outputFormat='<br/><a href="txmt://open?url=file://__FILE__&line=__LINE__">__FILE__</a> (Line __LINE__):<br/>__ERROR__<br/>'
			$JSLINT -process "$filePath" -output-format "$outputFormat" -context -nologo
		 else
			outputFormat='<a href="txmt://open?url=file://__FILE__&line=__LINE__">__FILE__</a> (Line __LINE__):<br/>__ERROR__<br/><br/>'
			$JSLINT -process "$filePath/*" -output-format "$outputFormat" +recurse -context -nofilelisting -nologo
	  fi
	  echo "<br/><br/>"
   done
else
   # THIS RUNS JSLINT ON THE CURRENT FILE
   filePath=$TM_FILEPATH
   outputFormat='<br/><a href="txmt://open?url=file://__FILE__&line=__LINE__">__FILE__</a> (Line __LINE__):<br/>__ERROR__<br/>'
   $JSLINT -process "$filePath" -output-format "$outputFormat" -context -nologo
fi


