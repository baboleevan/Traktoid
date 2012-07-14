package com.florianmski.tracktoid.trakt.tasks;

import android.app.Activity;
import android.widget.Toast;

import com.florianmski.tracktoid.db.DatabaseWrapper;
import com.florianmski.tracktoid.trakt.TraktManager;
import com.jakewharton.trakt.entities.Movie;

public class RemoveMovieTask extends TraktTask<Movie>
{
	private Movie movie;

	public RemoveMovieTask(TraktManager tm, Activity context, Movie movie) 
	{
		super(context);

		this.movie = movie;
	}

	@Override
	protected Movie doTraktStuffInBackground()
	{
		DatabaseWrapper dbw = new DatabaseWrapper(context);

		dbw.removeMovie(movie.url);

		dbw.close();

		showToast(movie.title + " removed!", Toast.LENGTH_SHORT);

		return movie;
	}

	@Override
	protected void sendEvent(Movie result) 
	{
		TraktTask.traktItemRemoved(movie);
	}

}