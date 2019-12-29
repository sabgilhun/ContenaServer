FROM ubuntu:latest

# Install cron
RUN apt-get update && apt-get -y install cron

# Install python
RUN apt-get update \
  && apt-get install -y python3-pip python3-dev \
  && cd /usr/local/bin \
  && ln -s /usr/bin/python3 python \
  && pip3 install --upgrade pip

# Copy python_script folder to root directory
COPY requirements.txt requirements.txt
RUN pip3 install -r requirements.txt

# Copy hello-cron file to the cron.d directory
COPY cron_job /etc/cron.d/cron_job

# Copy python_script folder to root directory
COPY python_script python_script

# Give execution rights on the cron job
RUN chmod 0644 /etc/cron.d/cron_job

# Apply cron job
RUN crontab /etc/cron.d/cron_job

# Create the log file to be able to run tail
RUN touch /var/log/cron.log

# Run the command on container startup
CMD cron && tail -f /var/log/cron.log