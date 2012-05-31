function logToLogfile(filename, message)
% Open log-file
[logfile, error_message] = fopen(filename, 'at');
if logfile == -1
   disp(sprintf('error during fopen of logfile (%s): %s', filename, error_message));
else
   fprintf(logfile, '%s\n', message);
   fclose(logfile);
end